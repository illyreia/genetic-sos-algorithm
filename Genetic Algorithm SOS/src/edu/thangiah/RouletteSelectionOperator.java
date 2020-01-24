package edu.thangiah;

import java.util.LinkedList;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * RouletteSelectionOperator is a Natural Selector that uses the concept of a 
 * Roulette wheel to select from a population of chromosomes.  Each chromosome's fitness is 
 * calculated individually and added together for a total fitness.  Then each chromosome is 
 * given a fitness value percentage related to the total fitness of all of the chromosomes.
 * A random number is generated from 0.0 to 100.0 just like you were spinning a Roulette wheel.
 * The chromosome that is stored in the NavigableMap at that value is selected to move into the 
 * next population.
 * 
 * @author Charlie Davis
 *
 */


public class RouletteSelectionOperator implements ISelector
{
	private Configuration configuration;
	private NavigableMap<Double, IChromosome> wheel;
	protected double totalFitness;
		
	public RouletteSelectionOperator(Configuration currentConfiguration)
	{
		configuration = currentConfiguration;
		wheel = new TreeMap<Double, IChromosome>();	
	}
		
	
	/**
	 * Get the current instance of the configuration of this RouletteSelectionOperator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration()
	{
		return configuration;
	}
	
	
	/**
	 * Method to select the chromosomes moving into the next population.  
	 * 
	 * 
	 * @param num_to_select
	 * @param population
	 */
	
	public void select(int numToSelect, Population population) 
	{
		// reset the total fitness to 0.0
		totalFitness = 0.0;
		
		double spin, currentFitnessRatio = 0.0;
		
		// create a temporary wheel for this selection
		NavigableMap<Double, IChromosome> tempWheel = getWheel();
		
		// get the current 
		RandomGenerator random = getConfiguration().getRandomGenerator();
		
		// make a temporary Linked List of chromosomes
		LinkedList<IChromosome> chromes = new LinkedList<IChromosome>();
		
		// for each IChromosome in the population
		// add to the totalFitness the current chromosome's fitness value
		for(IChromosome chrome : population.getChromosomes())
		{
			totalFitness += chrome.getFitnessValue();
		}
		
		// for each IChromosome in the population
		// set the fitness ratio of this chromosome
		// add the chromosome to the wheel with the fitnessRatio being the key
		// keep adding to the current fitness ratio to build our map keys
		for(IChromosome chrome : population.getChromosomes())
		{
			chrome.setFitnessRatio(chrome.getFitnessValue() / totalFitness);
			tempWheel.put(currentFitnessRatio, chrome);
			currentFitnessRatio += chrome.getFitnessRatio(); 
		}
		
		// for as many chromsomes to be selected 
		// spin the wheel get the chromosome from the map
		// and add it to our temporary chromosomes LinkedList
		
		for(int i = 0; i < numToSelect-1; i++)
		{
			spin = random.nextDouble();
			IChromosome tempChrome = tempWheel.get(tempWheel.floorKey(spin));
			chromes.add(tempChrome);
			
		}
			
			// finally add the fittest chromosome from the previous population 
			chromes.add(population.getFittestChromosome());
			
			// set the population to the new population we just created.
			population = new Population(getConfiguration(), chromes);
	}
	
	
	public TreeMap<Double,IChromosome> getWheel(){ return (TreeMap<Double, IChromosome>) wheel; }

}