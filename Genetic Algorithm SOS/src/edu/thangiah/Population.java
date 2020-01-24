package edu.thangiah;

import java.util.LinkedList;


/*
 * A Population is a generation of chromosomes that are stored in a list.
 * Population also has an instance of the current configuration and the 
 * last know fittest chromosome determined by the fitness function
 */

public class Population
{
	private IChromosome fittestChromosome;
	private LinkedList<IChromosome> chromosomes;
	private Configuration configuration;
	
	
	/*
	 * Constructor for the population takes in the current configuration as
	 * a parameter
	 */
	
	public Population(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		chromosomes = new LinkedList<IChromosome>();
		//Class clazz;
		for(int i = 0; i < configuration.getPopulationSize(); i++)
		{
			
			chromosomes.add(configuration.getSampleChromosome().newChromosome());
		}
	}
	
	public Population(Configuration currentConfiguration, LinkedList<IChromosome> chromes)
	{
		configuration = currentConfiguration;
		chromosomes = chromes;
	}
	
	
	private Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return configuration;
	}


	/*
	 * Add the given IChromosome to the list of chromosomes in the population
	 */
	
	public void addChromosome(final IChromosome chrome){ chromosomes.add(chrome); }
	
	public void addChromosome(int index, final IChromosome chrome){ chromosomes.add(index, chrome); }
	
	
	
	/*
	 * Set the IChromosome at the given index in the list of chromosomes in the population.
	 */
	
	public void setChromosome(final int index, final IChromosome chrome){ chromosomes.add(index, chrome); }
	
	
	/*
	 * Get an IChromosome from the list of chromosomes in the population at the given index
	 */
	
	public IChromosome getChromosome(final int index){ return chromosomes.get(index); }
	
	
	/*
	 * Get the entire list of chromosomes in the population
	 */
	
	public LinkedList<IChromosome> getChromosomes(){ return chromosomes; }
	
	
	/*
	 * Returns the size of the population.  Which is the number of chromosomes
	 * in the population
	 */
	
	public int size(){ return chromosomes.size(); }
	
	
	/*
	 * Remove a chromosome in the population at the given index.  
	 * Returns true if the remove was successful.
	 */
	
	public boolean removeChromosome(final int index)
	{ 
		if(chromosomes.remove(index) != null) 	return true; 
		else 									return false;
	}
	
	
	/*
	 *  Returns the last known fittest chromosome in this population
	 */
	
	public IChromosome getFittestChromosome()
	{
		return fittestChromosome;
	}
	
	
	/*
	 * Calculates the fittest chromosome in the population at current time
	 */
	
	public IChromosome calculateFittestChromosome()
	{
		//fittest_chromosome = null;
		IFitnessFunction fitnessFunction = configuration.getFitnessFunction();
		IChromosome currentFittest = chromosomes.get(0);
		
		double currentHighestFitness = 0.0, nextFitness = 0.0;
		
		for (int i = 1; i < chromosomes.size(); i++) 
		{
			currentHighestFitness = fitnessFunction.evaluate(currentFittest);
			nextFitness = fitnessFunction.evaluate(chromosomes.get(i));
			
            if(currentHighestFitness < nextFitness )
            {
            	currentFittest = chromosomes.get(i);
            }
        }
		
		return currentFittest; 
		
	}
	
	
	/*
	 * Manually set the fittest chromosome.
	 */
	
	
	public void setFittestChromosome(){ fittestChromosome = calculateFittestChromosome(); }
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		
		for(int i = 0; i < configuration.getPopulationSize(); i++)
		{
			if(i > 0) buffer.append(", ");
			
			buffer.append(chromosomes.get(i).toString());
		}
		
		buffer.append(" ]");
		
		return buffer.toString();
	}
	
}