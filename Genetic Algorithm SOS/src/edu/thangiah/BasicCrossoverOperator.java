package edu.thangiah;

import java.util.LinkedList;

/**
 * A BasicCrossoverOperator is an IGeneticOperator that takes the genes of two chromosomes
 * and crosses over their values.  This particular CrossoverOperator creates a random crossover point 
 * between two chromsomes and swaps their values making sure to fully fill in the rest of the chromosome
 * This is an important part of a  genetic algorithm.  
 * 
 * @author Charlie Davis
 *
 */


public class BasicCrossoverOperator implements IGeneticOperator
{
		private Configuration configuration;
		private int crossoverCount;
	
		public BasicCrossoverOperator(Configuration currentConfiguration)
		{
			configuration = currentConfiguration;
			crossoverCount = 0;
		}
		
		
		/**
		 * With the given population do crossover on the chromosomes 
		 * 
		 * @param pop
		 * @param chromes
		 * 
		 */
		
		public void operate(Population pop, LinkedList<IChromosome> chromes) 
		{
			RandomGenerator random = getConfiguration().getRandomGenerator();
			int chrome1, chrome2, crossoverPoint;
			int popSize = getConfiguration().getPopulationSize();
			int chromosomeSize = getConfiguration().getChromosomeSize();
			IChromosome chr1, chr2, resultChromosome1, resultChromosome2;
			LinkedList<IChromosome> tempChromes = new LinkedList<IChromosome>();
			
			for(IChromosome chrome : chromes)
			{
				double doCrossover;
				
				chrome1 = random.nextIntBetween(0, popSize-1);
				chrome2 = random.nextIntBetween(0, popSize-1);
				
				//resultChromosome1 = chr1.newChromosome();
				//resultChromosome2 = chr1.newChromosome();
				
				crossoverPoint = random.nextIntBetween(1, chromosomeSize-2);
				
				while(chrome1 == chrome2)
				{
					chrome2 = random.nextIntBetween(0, popSize-1);
				}
				
				chr1 = pop.getChromosome(chrome1);
				chr2 = pop.getChromosome(chrome2); 
				
				doCrossover = random.nextDouble();
				
				
				
				
				
				// if the random number is less than the crossover probability do the crossover
				
				if(doCrossover < getConfiguration().getCrossoverProbability()){
					LinkedList<IGene> tempChromosome1 = new LinkedList<IGene>();
					for(IGene g : chr1.getGenes()){
						tempChromosome1.addLast(g);
					}
					
					for(int i = crossoverPoint; i < configuration.getChromosomeSize(); i++){
						chr1.getGenes().set(i, chr2.getGenes().get(i));
						chr2.getGenes().set(i, tempChromosome1.get(i));
					}
					
					
				}
				
				//tempChromes.add(resultChromosome1);
			}
			//pop = new Population(getConfiguration(), tempChromes);
		}
		
		
		/**
		 * Get the configuration of this BasicCrossover Operator
		 * 
		 * @return configuration
		 */
		
		
		public Configuration getConfiguration(){ return configuration; }
		
		
		
}
