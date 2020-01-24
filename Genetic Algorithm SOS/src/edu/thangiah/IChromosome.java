package edu.thangiah;

import java.util.LinkedList;

/**
 * Chromosomes are a representation of a potential solution to the problem that is 
 * attempting to be solved by a genetic algorithm.  A chromosome consists of an
 * instance of the current configuration, a list of genes, a string indicating  
 * the type of gene that the chromosome is made of, the age of the chromosome, 
 * the calculated fitness value of the chromosome and the minimum and maximum fitness
 * values. IChromosome is the interface class that is implemented by AbstractChromosome. 
 * Any type of Chromosome that extends AbstractChromosome will need to implement the 
 * functionality of the this interface class. 
 * 
 */

public interface IChromosome
{
	/**
	 * Set the gene in the chromosome at the specific index
	 * 
	 * @param index, gene
	 */
	
	public void setGene(int index, IGene gene);
	
	
	/**
	 * Get gene at the given index
	 * 
	 * @param index
	 */
	
	public IGene getGene(int index);
	
	
	/**
	 * Get all of the genes in this chromosome
	 * 
	 * @return genes
	 */
	
	public LinkedList<IGene> getGenes();
	
	
	/**
	 * Get the internal value of the chromosome.  See BinaryGene for an example
	 */
	
	public int getValue();
	
	
	/**
	 * Set fitness of this chromosome 
	 * 
	 * @param currentFitness
	 */
	
	public void setFitnessValue(double currentFitness);
	
	
	/**
	 * Get the current fitness value of this chromosome
	 * 
	 * @return fitness
	 */
	
	public double getFitnessValue();
	
	
	/**
	 * Set the fitness ratio of the chromosome
	 */
	
	public void setFitnessRatio(double currentFitnessRatio);
	
	
	/**
	 * Get the last know fitness ratio of the chromosome
	 */
	
	public double getFitnessRatio();
	
	
	/**
	 * Set the minimum fitness value of the chromosome
	 */
	
	public void setMinFitnessValue(double fitnessValue);
	
	
	/**
	 * Set the maximum fitness value of the chromomsome
	 */
	
	public void setMaxFitnessValue(double fitnessValue);
	
	
	/**
	 * Get the minimum fitness value of the chromosome
	 */
	
	public double getMinFitnessValue();
	
	
	/**
	 * Get the maximum fitness value of the chromosome
	 */
	
	public double getMaxFitnessValue();
	
	
	/**
	 * Returns the size of the chromosome
	 */
	
	public int size();
	
	
	/**
	 * Increment the age of this chromosome by one
	 * 
	 */
	
	public void increaseAge();
	
	
	/**
	 * Set each gene in the chromosome to a random value
	 * 
	 * @param generator
	 */
	
	public void setRandomGenes(RandomGenerator generator);
	
	
	/**
	 * Returns a new Instance of this chromosome as a clone
	 * 
	 * @return new IChromosome
	 */
	
	public IChromosome newChromosome();
	
}