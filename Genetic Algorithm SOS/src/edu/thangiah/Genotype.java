package edu.thangiah;

import java.util.LinkedList;

/**
 * A Genotype is the represetation of the entire problem you are trying to solve.
 * Genotype contains the population, a configuration of the current problem, a fitness
 * function and the fittest chromosome in the Genotype
 * 
 * 
 * @author Charlie Davis
 *
 */

public class Genotype
{
	private Population population;
	private Configuration configuration;
	private AbstractFitnessFunction fitnessFunction;
	private int chromosomeSize;
	private IChromosome fittestChromosome;
	private transient Configuration lastConf;
	private transient Population lastPop;
	
	
	public Genotype(Configuration config)
	{
		configuration = config;
		fitnessFunction = configuration.getFitnessFunction();
		chromosomeSize = configuration.getChromosomeSize();
		lastPop = null;
	}
	
	public Genotype(Configuration config, Population pop)
	{
		this(config);
		setPopulation(pop);
	}
	
	
	/**
	 * This method randomizes the initial population to random chromosomes of the given
	 * gene type found in the configuration
	 * 
	 * @param populationSize
	 */
	
	public void randomizeInitialPopulation(int populationSize)
	{
		RandomGenerator generator = configuration.getRandomGenerator();
		
		// for each chromosome in the population set its genes to random values
		for(IChromosome tempChromosome : getPopulation().getChromosomes())
		{
			tempChromosome.setRandomGenes(generator);
		}
		
	}
	
	
	/**
	 * 
	 * @param configuration
	 * @return randomized Genotype
	 */
	
	
	public static Genotype randomInitialGenotype(Configuration configuration)
	{
		if (configuration.getCurrentGenerationNum() == 0) 
		{
		    if (configuration == null) {
		      throw new IllegalArgumentException(
		          "The Configuration instance may not be null.");
		    }
		    configuration.lockSettings();
		    // Create an array of chromosomes equal to the desired size in the
		    // active Configuration and then populate that array with Chromosome
		    // instances constructed according to the setup in the sample
		    // Chromosome, but with random gene values (alleles). The Chromosome
		    // class randomInitialChromosome() method will take care of that for
		    // us.
		    // ------------------------------------------------------------------
		    int populationSize = configuration.getPopulationSize();
		    Population pop = new Population(configuration);
		    // Do randomized initialization.
		    // -----------------------------
		    Genotype result = new Genotype(configuration, pop);
		    result.randomizeInitialPopulation(populationSize);
		    
		    return result;
		}
		
		return null;
	}
	
	
	/**
	 * Evolve actually does the work of applying natural selectors and genetic operators
	 * 
	 */
	
	public void evolve()
	{
		//System.out.println("Before Evolve " + population.toString());
		Population tempPop = population;
		lastPop = population;
		
		IFitnessFunction fitnessFunction = getConfiguration().getFitnessFunction();
		IChromosome fittest = null;
		
		int size = population.size();
		
		// for every chromosome in the population
		// get the chromosome and increase its age
		// evaluate its fitness
		
		for (int i = 0; i < size; i++)
		{
			IChromosome chrom = tempPop.getChromosome(i);
	        chrom.increaseAge();
	        chrom.setFitnessValue(fitnessFunction.evaluate(chrom));
	    }
		
		// apply all Genetic operators and NaturalSelectors
		
		applyNaturalSelectors(getConfiguration(), tempPop);
		applyGeneticOperators(getConfiguration(), tempPop);
		
		
		// increment the current generation number
		
		configuration.incrementGenerationNum();
		lastConf = configuration;
		
		// Save the fittest chromosome
		// set the population with the new population
		setFittestChromosome(calculateFittestChromosome());
		setPopulation(tempPop);
		
		//System.out.println("After Evolve " + population.toString());
	}
	
	
	/**
	 * Get the current population
	 * 
	 * @return population
	 */
	
	public Population getPopulation()
	{
		return population;
	}
	
	
	/**
	 * Get the last population
	 * 
	 * @return lastPop
	 */
	
	public Population getLastPopulation()
	{
		return lastPop;
	}
	
	
	/**
	 * Set the current population with the new population 
	 * 
	 * @param newPopulation
	 */
	
	public void setPopulation(Population newPopulation)
	{
		population = newPopulation;
	}
	
	
	/**
	 * Set the last population 
	 * 
	 * @param newPopulation
	 */
	
	public void setLastPopulation(Population newPopulation)
	{
		lastPop = newPopulation;
	}
	
	
	/**
	 * Get the configuration of this Genotype
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration()
	{
		return configuration;
	}
	
	
	/**
	 * Get the fittest chromosome in the this Genotype
	 * 
	 * @return fittest_chromosome
	 */
	
	public IChromosome getFittestChromosome()
	{
		return fittestChromosome;
	}
	
	
	/**
	 * Set the the fittest chromosome of this Genotype
	 * 
	 * @param chrome
	 */
	
	public void setFittestChromosome(IChromosome chrome)
	{
		fittestChromosome = chrome;
	}
	
  /**
   * Applies all GeneticOperators registered with the Configuration.
   *
   * @param currentConfig the configuration to use
   * @param currentPopulation the population to use as input
   *
   */

	
	public IChromosome calculateFittestChromosome()
	{
		//fittest_chromosome = null;
		IFitnessFunction fitnessFunction = configuration.getFitnessFunction();
		IChromosome currentFittest = getPopulation().getChromosomes().get(0);
		
		double currentHighestFitness = 0.0, nextFitness = 0.0;
		
		for (int i = 1; i < population.size(); i++) 
		{
			currentHighestFitness = fitnessFunction.evaluate(currentFittest);
			nextFitness = fitnessFunction.evaluate(population.getChromosomes().get(i));
			
            if(currentHighestFitness < nextFitness )
            {
            	currentFittest = population.getChromosomes().get(i);
            }
        }
		
		setFittestChromosome(currentFittest);
		return currentFittest; 
		
	}
	
	
  protected void applyGeneticOperators(Configuration currentConfig, Population currentPopulation) 
  {
	    LinkedList<IGeneticOperator> geneticOperators = currentConfig.getGeneticOperators();
	    
	    // for each operator in the list of genetic operators
	    // operate!
	    
	    for(IGeneticOperator operator : geneticOperators)
	    {
	      IGeneticOperator tempOperator = (IGeneticOperator) operator;
	      tempOperator.operate(currentPopulation, currentPopulation.getChromosomes());
	      
	    }
  }
  
  
  /**
   * Applies all NaturalSelectors registered with the Configuration.
   *
   * @param currentConfig the configuration to use
   * @param currentPopulation the population to use as input
   *
   */
  
  protected void applyNaturalSelectors(Configuration currentConfig, Population currentPopulation)
  {
	  LinkedList<ISelector> selectors = currentConfig.getSelectors();
	  int numToSelect = currentPopulation.size();
	  
	  // for each Selector in the list of Natural Selectors
	  // Select!
	  
	  for(ISelector selector: selectors)
	  {
		  ISelector tempSelector = (ISelector) selector;
		  tempSelector.select(numToSelect, currentPopulation);
	  }
  }
	  
	
}