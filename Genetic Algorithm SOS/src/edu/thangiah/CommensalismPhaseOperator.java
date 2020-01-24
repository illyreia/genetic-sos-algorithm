package edu.thangiah;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;


/**
 * 
 * CommensalismPhaseOperator takes in the current population
 * and randomly selects an organism to calculate alongside an iteration of the ecosystem.
 * It calculates a new fitness value based on a random vector, and the fitness values of 
 * another organism.  It rejects or accepts the new organisms based on their new fitness
 * values in relation to the old values.
 *
 */

public class CommensalismPhaseOperator extends MutationOperator
{
	private Configuration configuration;

	/**
	 * Most basic constructor for the CommensalismPhaseOperator 
	 * sets the values of this MutationOperator based on the currentConfiguration
	 * 
	 * @param currentConfiguration
	 */
	
	public CommensalismPhaseOperator(Configuration currentConfiguration)
	{	
			configuration = currentConfiguration;
	}
	
	
	
	/**
	 * Returns the configuration of this CommensalismPhase Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	/**
	 * CommensalismPhaseOperator takes in the current population
	 * and randomly selects an organism to calculate alongside an iteration of the ecosystem.
	 * It calculates a new fitness value based on a random vector, and the fitness values of 
	 * another organism.  It rejects or accepts the new organisms based on their new fitness
	 * values in relation to the old values.  
	 * 
	 * @param pop
	 * @param chromo
	 * 
	 */
	
	public void operate(Population pop, LinkedList<IChromosome> chromo)
	{
		IChromosome chr1, chr2;
		double bestFit = pop.calculateFittestChromosome().getFitnessValue();
		double chromo1, chromo2;
		String test ="", test2 = "";
		int chromofit1, chromofit2;
		//System.out.println(pop.calculateFittestChromosome().getFitnessValue() + " fittest currently");
		//since calculatefittestchromosome() isn't actually picking the most fit, calculating bestFit
		for(int k = 0; k <pop.size(); k++) {
			if(pop.getChromosome(k).getFitnessValue() > bestFit)
					bestFit = pop.getChromosome(k).getFitnessValue();
			else continue;
		}
			for(int i = 0; i <pop.size(); i++) {
				chr1 = pop.getChromosome(i);
				int random = (int)(Math.random() * pop.size());
				chr2 = pop.getChromosome(random);
				
				//traverses chromosome(i), and chromosome(random) and takes the representation of the genes
				//stores them into a string, which is then parsed from a binary string to an integer
				//essentially finds the fitness value, but using the actual binary gene 
				//representaion rather than getFitnessValue()
				for(int k = 0; k < chr2.getGenes().size(); k++) {
					test = test + pop.getChromosome(i).getGene(k);
					test2 = test2 + pop.getChromosome(random).getGene(k);
				}
				chromofit1 = Integer.parseInt(test,2);
				chromofit2 = Integer.parseInt(test2,2);
				
				//reset the strings, so it doesn't keep appending
				test="";			
				test2="";
				
				chr1.setMaxFitnessValue(65535);		
				chr2.setMaxFitnessValue(65535);		
				/*
				 * getMaxFitnessValue keeps returning 0 ?
				chr1.getMaxFitnessValue();
				chr1.getMaxFitnessValue();
				*/
				if(chr1==chr2) {
					continue;
				}
				else if (chr1 != chr2) {
					chromo1 =  (chromofit1 + (int)(Math.random() * 2 - 1)) * (bestFit - chromofit2);
					if(chromo1 > chromofit1 && chromo1 <= chr1.getMaxFitnessValue()) {
						String representation = Integer.toBinaryString((int) chromo1);	//string of chromosome's binary values
						//System.out.println(chromofit1 + " changed to: " + chromo1);
						System.out.println(pop.getChromosome(random).getGenes() + " changed to: " + representation + "through Commensalism.");
						System.out.println(pop.getChromosome(i).getFitnessValue() + " changed to: " + chromo1+ "through Commensalism.");
						//pop.getChromosome(i).setFitnessValue(chromo1);
						
						//traverses string of chromosome's binary values, replacing old alleles with better ones
						for(int k = 0; k < chr1.getGenes().size(); k++) {
							//System.out.println(representation + " representation with fitness of: " + 
									//pop.getChromosome(random).getFitnessValue());
							for(int m = 0; m < representation.length(); m++) {
							    int n = Character.digit(representation.charAt(m), 10);
							    pop.getChromosome(i).getGene(m).setAllele(n);

							}
							//System.out.print(pop.getChromosome(random).getGenes());
						}
						//System.out.println();
					}
					else continue;
				}
			}
			/*for (int i = 0; i < pop.size(); i++) {	//100
				System.out.println("Chromosome values: " + pop.getChromosome(i).getFitnessValue());
			}*/
	}
	
}