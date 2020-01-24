package edu.thangiah;


/**
 * A fitness function is arguably the most important part of a genetic algorithm.
 * The fitness function evaluates chromosomes to determine their fitness against a known 
 * metric.  It is up to the user to decide how fit a chromosome is for the population
 * 
 * @author Charles Davis
 *
 */

public interface IFitnessFunction
{
	
	/**
	 * Abstract function that evaluates the fitness of a given chromosome
	 * It is the responsibility of the user to decided how to calculate the 
	 * fitness of the chromosomes in the population
	 * 
	 * @param IChromosome chrome
	 */
	
	public double evaluate(IChromosome chrome);
	
}