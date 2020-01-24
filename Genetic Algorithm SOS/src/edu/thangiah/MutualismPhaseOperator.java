package edu.thangiah;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;



/**
 * 
 * MutualismPhaseOperator takes in the current population
 * and randomly selects an organism to calculate alongside an iteration of the ecosystem.
 * It rejects the modified organisms if they're not more fit than the previous 
 * 
 *
 */




public class MutualismPhaseOperator extends MutationOperator
{
	//private int [] genesToMutate;
	private Configuration configuration;
	//int mutated;
	
	/**
	 * Most basic constructor for the MutualismPhaseOperator 
	 * sets the values of this MutationOperator based on the currentConfiguration
	 * 
	 * @param currentConfiguration
	 */
	
	public MutualismPhaseOperator(Configuration currentConfiguration)
	{	
			configuration = currentConfiguration;
	}
	

	
	/**
	 * Returns the configuration of this MutualismPhaseOperator Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	
	/**
	 * The operate method for the MutualismPhaseOperator takes in the current population
	 * and randomly selects an organism to calculate alongside an iteration of the ecosystem.
	 * Rejects the modified organisms if they're not better than the previous  
	 * @param pop
	 * @param chromo
	 * 
	 */
	
	public void operate(Population pop, LinkedList<IChromosome> chromo)
	{
		IChromosome chr1, chr2;
		double bestFit = pop.calculateFittestChromosome().getFitnessValue();
		double chromo1, chromo2;
		int chromofit1, chromofit2;
		String test = "", test2 = "";
			//can just use pop.calculateFittestChromosome().getFitnessValue()?
			/*for (int i = 0; i < pop.size(); i++) {	//100
				System.out.println("Chromosome values: " + pop.getChromosome(i).getFitnessValue());
			}*/
			//System.out.println(bestFit + " = best fitness");
			//System.out.println(pop.calculateFittestChromosome().getFitnessValue() + " fittest currently");
		
			for(int i = 0; i <pop.size(); i++) {
				chr1 = pop.getChromosome(i);
				int random = (int)(Math.random() * pop.size());
				chr2 = pop.getChromosome(random);
				
				//traverses chromosome(i), and chromosome(random) and takes the representation of the genes
				//stores them into a string, which is then parsed from a binary string to an integer
				//essentially finds the fitness value, but using the actual binary gene 
				//representaion rather than getFitnessValue()
				for(int k = 0; k < chr1.getGenes().size(); k++) {

					test = test + pop.getChromosome(i).getGene(k);
					test2 = test2 + pop.getChromosome(random).getGene(k);
				}
				chromofit1 = Integer.parseInt(test,2);
				chromofit2 = Integer.parseInt(test2,2);

				//reset the strings, so it doesn't keep appending
				test="";			
				test2="";
				
				//System.out.println(chromofit1);
				//System.out.println(chromofit2);
				
				//chr1.setMaxFitnessValue(65535);		//changing it to 65,535 usually means mutualismphase's value is never taken
				//chr2.setMaxFitnessValue(65535);		//so using 200,000 to test

				if(chr1==chr2) {
					continue;
				}
				else if (chr1 != chr2) {
					double mutualVector = (chromofit1 + chromofit2)/2;
					double BF1 = (int)(Math.random() * 2 + 1);
					double BF2 = (int)(Math.random() * 2 + 1);
					chromo1 =  Math.round((chromofit1 + (int)(Math.random() + 1) * (bestFit - mutualVector * BF1)));
					chromo2 =  Math.round((chromofit2 + (int)(Math.random() + 1) * (bestFit - mutualVector * BF2)));
					//System.out.println("chr1 fitness value: " + chr1.getFitnessValue() + " Chromo 1: " + 
						//chromo1 + " chr2 fitness value: " + chr2.getFitnessValue() +" Chromo 2: " + chromo2);
					if(chromo1 > chromofit1 && chromo1 <= chr1.getMaxFitnessValue()) {
						String representation = Integer.toBinaryString((int) chromo1);	//string of chromosome's binary values
						System.out.println(chromofit1 + " changed to: " + chromo1+ " through Mutualism.");
						System.out.println(pop.getChromosome(i).getGenes() + " changed to: " + representation + " through Mutualism.");
						//System.out.println(pop.getChromosome(i).getFitnessValue() + " changed to: " + chromo1);

						//pop.getChromosome(i).setFitnessValue(chromo1);
						//chromofit1 = (int) chromo1;
						
						//traverses string of chromosome's binary values, replacing old alleles with better ones
						for(int k = 0; k < chr1.getGenes().size(); k++) {
							//String representation = Integer.toBinaryString((int) chromo1);
							//System.out.println(representation + " representation with fitness of: " + 
									//pop.getChromosome(i).getFitnessValue());
							//String number = String.valueOf(representation);
							for(int m = 0; m < representation.length(); m++) {
							    int n = Character.digit(representation.charAt(m), 10);
							    pop.getChromosome(i).getGene(m).setAllele(n);
								/*for( int y = 0; y <  chr1.getGenes().size()-number.length(); y++) {
									pop.getChromosome(i).getGene(k).setAllele(0);
								}*/

							}
							//System.out.print(pop.getChromosome(i).getGenes());
						}
						//System.out.println();
						
					}
					else continue;
					if(chromo2 > chromofit2 && chromo2 <= chr2.getMaxFitnessValue())
					{
						String representation = Integer.toBinaryString((int) chromo2);	//string of chromosome's binary values
						System.out.println(chromofit2 + " changed2 to: " + chromo2+ " through Mutualism.");
						System.out.println(pop.getChromosome(random).getGenes() + " changed to: " + representation + " through Mutualism.");

						//pop.getChromosome(random).setFitnessValue(chromo2);
						//chromofit1 = (int) chromo1;
						
						//traverses string of chromosome's binary values, replacing old alleles with better ones
						for(int k = 0; k < chr2.getGenes().size(); k++) {
							//System.out.println(representation + " representation with fitness of: " + 
									//pop.getChromosome(random).getFitnessValue());
							//String number = String.valueOf(representation);
							//System.out.println(number + " number length");
							for(int m = 0; m < representation.length(); m++) {
							    int n = Character.digit(representation.charAt(m), 10);
							    pop.getChromosome(random).getGene(m).setAllele(n);

							}
							//System.out.print(pop.getChromosome(random).getGenes());
						}
						//System.out.println();
					}
					else continue;
				}
			}

	}
	
}