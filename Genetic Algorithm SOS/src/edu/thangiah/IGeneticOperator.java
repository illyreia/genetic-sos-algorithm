package edu.thangiah;

import java.util.LinkedList;


/**
 * Genetic operators are an essential part of evolutionary algorithms.  A genetic operator
 * is an algorithm that will operate on a LinkedList of IChromosomes to modify it in some way.  
 * Two examples are BasicCrossoverOperator.java and MutationOperator.java that both implement
 * this interface.
 * 
 * @author Charlie Davis
 *
 */

public interface IGeneticOperator {
	
	
	/**
	 * From the given population get the list of chromosomes to be operated on
	 * 
	 * @param pop
	 * @param chromo
	 */
	
	
	public void operate(Population pop, LinkedList<IChromosome> chromo);
	
}
