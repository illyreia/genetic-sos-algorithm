package edu.thangiah;

/**
* Genetic operators are an essential part of evolutionary algorithms.  A genetic operator
* is an algorithm that will operate on a LinkedList of IChromosomes to modify it in some way.  
* Two examples are BasicCrossoverOperator.java and MutationOperator.java that both implement
* the IGeneticOperator interface
* 
* @author Charlie Davis
*
*/

public abstract class AbstractGeneticOperator implements IGeneticOperator{
	private Configuration configuration;
	
	/**
	 * Get the configuration of this Genetic Operator
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){
		return configuration;
	}
}
