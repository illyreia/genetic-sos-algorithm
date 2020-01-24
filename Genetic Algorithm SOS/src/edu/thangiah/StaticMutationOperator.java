package edu.thangiah;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;



/**
 * 
 * StaticMutationOperation is a MutationOperator that decides at the beginning of runtime 
 * which genes in which generations will be mutated.  It does this by using a random number
 * generator that generates a certain number of genes indexes to be mutated based on the
 * mutation rate, chromosome size, population size and number of generations.
 * 
 * @author Charlie
 *
 */




public class StaticMutationOperator extends MutationOperator
{
	private int [] genesToMutate;
	private Configuration configuration;
	int mutated;
	
	/**
	 * Most basic constructor for the StaticMutationOperator 
	 * sets the values of this MutationOperator based on the currentConfiguration
	 * 
	 * @param currentConfiguration
	 */
	
	public StaticMutationOperator(Configuration currentConfiguration)
	{	
			configuration = currentConfiguration;
			double currentMutationRate = getConfiguration().getMutationProbability();
			setMutationRate(currentMutationRate);
			initializeStaticValues();
	}
	
	
	/**
	 * Set the array of genes that are going to be mutated for the whole run
	 * 
	 * @param genesToMutate
	 */
	
	public void setGenesToMutate(int [] genesToMutate)
	{
		this.genesToMutate = genesToMutate;
	}
	
	
	/**
	 * Get the genes to be mutated for the whole run of the program
	 * 
	 * 
	 * @return genes_to_mutate
	 */
	
	public int [] getGenesToMutate()
	{
		return genesToMutate;
	}
	
	
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
		
		RandomGenerator random = getConfiguration().getRandomGenerator();
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
	}
	
	
	/**
	 * The operate method for the StaticMutationOperator takes in the current population
	 * and decides whether or not a given gene is to be mutated based on the initialization
	 * of the genes_to_be_mutated array.  
	 * 
	 * @param pop
	 * @param chromo
	 * 
	 */
	
	public void operate(Population pop, LinkedList<IChromosome> chromo)
	{
		
		double mutationRate = getMutationRate();
		int numGenerations = getConfiguration().getNumGenerations();
		int numChromosomes = getConfiguration().getPopulationSize();
		int numGenesInChromosome = getConfiguration().getChromosomeSize();
		
		
		// number of total genes in the whole geno type
		int totalGenes = numGenerations * numChromosomes * numGenesInChromosome;
		
		
		int currentGeneration = getConfiguration().getCurrentGenerationNum();
		int chromeNum, geneNum, tempGeneIndex;
		
		//bolean used to see if current gene is found in the genes_to_mutate array
		boolean contains = false;
		
		
		// total number of genes in a population
		int totalGenesInPop = numChromosomes*numGenesInChromosome;
		
		//get a local copy of the genes to be mutated
		int [] genesToMutate = getGenesToMutate();
		
		// for each gene in the population check to see if it should be mutated
		for(int i = 0; i < totalGenesInPop; i++)
		{
			// tempGeneIndex is the overall gene number to be checked against the gene_to_mutate array
			tempGeneIndex = (numGenesInChromosome * numChromosomes * currentGeneration) + i;
			
			// for every gene in the genesToMutate array check it
			for(int j = 0; j < genesToMutate.length; j++)
			{
				// if it is in there contains is true
				if(tempGeneIndex == genesToMutate[j])	contains = true;
			}
			
			// if it is contained in there let's mutate it
			if(contains)
			{
				chromeNum = i / numGenesInChromosome;
				
				// cannot divided by zero
				if(chromeNum == 0)
				{
					geneNum = i;
				}else
				{
					geneNum = i % chromeNum;
				}
				
				// get the gene and mutate it
				pop.getChromosome(chromeNum).getGene(geneNum).mutate();
				
				//flip contains back to false for next iteration
				contains = false;
				
			}
			
		}
		
	}
	
}