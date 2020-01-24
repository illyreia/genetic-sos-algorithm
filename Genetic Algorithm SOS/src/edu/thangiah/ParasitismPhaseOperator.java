package edu.thangiah;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;


/**
 * 
 * ParasitismPhaseOperator takes in the current population
 * and randomly selects an organism to calculate alongside an iteration of the ecosystem.
 * Creates a new parasiteVector with the fitness values of the current iteration organisms,
 * and calculates a new fitness value based on a random number calculation.  
 * If the parasiteVector's fitness value is greater than the random organisms, the random
 * organism is replaced with the parasiteVector.  If the previous fitness value was greater,
 * parasiteVector is cleared.
 *
 */




public class ParasitismPhaseOperator extends MutationOperator
{
	//private int [] genesToMutate;
	private Configuration configuration;
	//int mutated;
	
	/**
	 * Most basic constructor for the StaticMutationOperator 
	 * sets the values of this MutationOperator based on the currentConfiguration
	 * 
	 * @param currentConfiguration
	 */
	
	public ParasitismPhaseOperator(Configuration currentConfiguration)
	{	
			configuration = currentConfiguration;
			/*double currentMutationRate = getConfiguration().getMutationProbability();
			setMutationRate(currentMutationRate);
			initializeStaticValues();*/
	}
	
	
	/**
	 * Set the array of genes that are going to be mutated for the whole run
	 * 
	 * @param genesToMutate
	 */
	
	/*public void setGenesToMutate(int [] genesToMutate)
	{
		this.genesToMutate = genesToMutate;
	}*/
	
	
	/**
	 * Get the genes to be mutated for the whole run of the program
	 * 
	 * 
	 * @return genes_to_mutate
	 */
	
	/*public int [] getGenesToMutate()
	{
		return genesToMutate;
	}*/
	
	
	/**
	 * Returns the configuration of this StaticMutation Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	/**
	 * Method to initialize all of the static values of the genes to be 
	 * mutated.  
	 * 
	 */
	
	public void initializeStaticValues()
	{
		
		/*RandomGenerator random = getConfiguration().getRandomGenerator();
		double mutationRate = getMutationRate();
		int numGenerations = getConfiguration().getNumGenerations();
		int numChromosomes = getConfiguration().getPopulationSize();
		int numGenes = getConfiguration().getChromosomeSize();
		
		// total amount of genes in the entire run of the Genoype
		int totalGenes = numGenerations * numChromosomes * numGenes;
		
		// total number of genes to be mutated based on mutation rate
		int totalMutations = (int)(totalGenes * mutationRate);
		
		// get a temp array of random ints that don't repeat
		// values can be from 0 to total number of genes in the Genotype
		// fill it with as mant integers as there are totalMutations
		int [] temp = random.nextNonRepeatingRandomIntArray(0, totalGenes, totalMutations);
		
		//sort the array
		Arrays.sort(temp);
		
		// set it to the main array of genes to be mutated.
		setGenesToMutate(temp);
		*/
	}
	
	
	/**
	 * ParasitismPhaseOperator takes in the current population
	 * and randomly selects an organism to calculate alongside an iteration of the ecosystem.
	 * Creates a new parasiteVector with the fitness values of the current iteration organisms,
	 * and calculates a new fitness value based on a random number calculation.  
	 * If the parasiteVector's fitness value is greater than the random organisms, the random
	 * organism is replaced with the parasiteVector.  If the previous fitness value was greater,
	 * parasiteVector is cleared.
	 * @param pop
	 * @param chromo
	 * 
	 */
	
	public void operate(Population pop, LinkedList<IChromosome> chromo)
	{
		IChromosome chr1, chr2;
		double bestFit = pop.calculateFittestChromosome().getFitnessValue();
		double chromo1, chromo2;
		Vector<IChromosome> parasiteVector = new Vector();
		String test ="", test2 = "";
		int chromofit1, chromofit2;
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
				
				parasiteVector.add(pop.getChromosome(i));
				
				if(chr1==chr2) {
					continue;
				}
				else if (parasiteVector.lastElement() != chr2) {
					//modifies the search space by duplicating X[i] into parasiteVector
					//then comparing a random chromosome with the parasiteVector
					parasiteVector.lastElement().setMaxFitnessValue(chr1.getMaxFitnessValue());
					
					//if greater, replace the randomly selected organism with parasiteVector value
					if(parasiteVector.lastElement().getFitnessValue() > chromofit2) {
						System.out.println(pop.getChromosome(random).getFitnessValue() + 
								" changed to: " + (parasiteVector.lastElement().getFitnessValue()) + " through Parasitism.");
						pop.getChromosome(random).setFitnessValue(parasiteVector.lastElement().getFitnessValue());
						parasiteVector.clear();
						/*for(int j=0; j<parasiteVector.size();j++)
							System.out.println(parasiteVector.get(j).getFitnessValue());
							*/
					}
					//if not greater, clear parasiteVector
					else if(parasiteVector.lastElement().getFitnessValue() <= chromofit2)
						parasiteVector.clear();
				}		
			}
			System.out.println("Real best fitness: " + bestFit);
			System.out.println("final representation (should match most fit chromosome "
					+ "of this generation): " + Integer.toBinaryString((int) bestFit));
	}
}