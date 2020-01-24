package edu.thangiah;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Chromosomes are a representation of a potential solution to the problem that is 
 * attempting to be solved by a genetic algorithm.  A chromosome consists of an
 * instance of the current configuration, a list of genes, a string indicating  
 * the type of gene that the chromosome is made of, the age of the chromosome, 
 * the calculated fitness value of the chromosome and the minimum and maximum fitness
 * values. AbstractChromsome implements IChromosome.  Any type of Chromosome that
 * extends AbstractChromosome will need to implement the functionality of the
 * interface class.  See IChromosome.java for all of the necessary functions. 
 * 
 */

public abstract class AbstractChromosome implements IChromosome, Serializable
{	
	private Configuration configuration;
	private LinkedList<IGene> genes;
	private double fitness;
	private int age;
	private double fitnessRatio;
	private double minFitnessValue;
	private double maxFitnessValue;
	private int size;
	
	
	/**
	 * Basic constructor for a chromosome
	 * 
	 * @param currentConfiguration
	 * 
	 * */
	
	public AbstractChromosome(Configuration currentConfiguration)
	{ 
		configuration = currentConfiguration; 
		genes = new LinkedList<IGene>();
	}
	
	
	/**
	 * Set the minimum fitness value of this chromosome
	 * 
	 * @param min
	 */
	
	public void setMinFitnessValue(double min){ minFitnessValue = min; }
	
	
	/**
	 * Set the maximum fitness value of this chromosome
	 * 
	 * @param max
	 */
	public void setMaxFitnessValue(double max){ maxFitnessValue = max; }
	
	
	/**
	 * Get the maximum fitness value of this chromosome
	 * 
	 * @return min_fitness_value
	 */
	
	public double getMinFitnessValue(){ return minFitnessValue; }
	
	
	/**
	 * Get the maximum fitness value of this chromosome
	 * 
	 * @return max_fitness_value
	 */
	
	public double getMaxFitnessValue(){ return maxFitnessValue; }
	

	/**
	 * Add a gene to the chromosome
	 * 
	 * @param gene
	 */
	
	public void addGene(IGene gene){ genes.add(gene); }
	
	
	/**
	 * Set the gene in the chromosome at the specific index
	 * 
	 * @param index, gene
	 */
	
	public void setGene(int index, IGene gene){ genes.set(index, gene); }
	
	
	/**
	 * Set this chromosome's genes with a LinkedList of new genes
	 * 
	 * @param currentGenes
	 */
	
	public void setGenes(LinkedList<IGene> currentGenes){ genes = currentGenes; }
	
	
	/**
	 * Get gene at the given index
	 * 
	 * @param index
	 */
	
	public IGene getGene(int index){ return genes.get(index); }
	
	
	/**
	 * Get all of the genes in this chromosome
	 * 
	 * @return genes
	 */
	
	public LinkedList<IGene> getGenes(){ return genes; }
	
	
	/**
	 * Set fitness of this chromosome 
	 * 
	 * @param currentFitness
	 */
	
	public void setFitnessValue(double currentFitness){ fitness = currentFitness; }
	
	
	/**
	 * Get the current fitness value of this chromosome
	 * 
	 * @return fitness
	 */
	
	public double getFitnessValue(){ return fitness; }
	
	
	/**
	 * Returns the size of the chromosome
	 * 
	 * @return size of chromosome
	 */
	
	public int size(){ return genes.size(); }
	
	
	/**
	 * Set the fitness ratio of this chromosome.  
	 * The fitness ratio is calculate by dividing this chromosomes fitness value 
	 * by the total of all of the fitness vaules of the chromosomes in the population
	 * 
	 * @param currentFitnessRatio
	 */
	
	public void setFitnessRatio(double currentFitnessRatio){ fitnessRatio = currentFitnessRatio; }
	
	
	/**
	 * Get the current fitness ratio of this chromosome
	 * 
	 * @return fitness_ratio
	 */
	
	public double getFitnessRatio(){ return fitnessRatio; }
	
	
	/**
	 * Get the current configuration of the this chromosome
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	/**
	 * Set the age of this chromosome
	 * 
	 * @param currentAge
	 */
	
	public void setAge(int currentAge){ age = currentAge; }
	
	
	/**
	 * Get the current age of the chromosome
	 * 
	 * @return
	 */
	
	public int getAge(){ return age; }
	
	
	/**
	 * Increment the age of this chromosome by one
	 * 
	 */
	
	public void increaseAge(){ age++; }
	
	
	/**
	 * newChromosomeInternal is to be implemented by the class that extends 
	 * AbstractChromosome.  It is the responsibility of the child class to define what 
	 * a new chromosome is.  See Chromosome.java for example.
	 * 
	 * @return new IChromosome
	 */
	
	public abstract IChromosome newChromosomeInternal();
	
	
	/**
	 * Returns a new Instance of this chromosome as a clone
	 * 
	 * @return new IChromosome
	 */
	
	public IChromosome newChromosome()
	{
		
		return newChromosomeInternal();
	}
	
	
	/**
	 * Returns the string representation of a chromosome
	 * 
	 * @return this.toString()
	 */
	
	public String toString()
	{
			StringBuffer buffer = new StringBuffer();
			buffer.append("[ ");
			
			for(int i = 0; i < getConfiguration().getChromosomeSize(); i++)
			{
				if(i > 0) buffer.append(", ");
				
				buffer.append(genes.get(i).toString());
			}
			
			buffer.append(" ]");
			
			return buffer.toString();
	}
	
}