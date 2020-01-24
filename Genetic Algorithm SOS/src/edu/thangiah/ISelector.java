package edu.thangiah;

/**
 * A selection operator is another type of operator essential to an evolutionary 
 * algorithm.  Any classes that implement this interface will decided how the chromosomes
 * are selected for the next population generation.  
 * 
 * @author Charlie
 *
 */


public interface ISelector {
	
	/**
	 * Selects the number of chromosomes from the current population to move into the next
	 * population.
	 * 
	 * @param num_to_select
	 * @param population
	 */
	
	public void select(int numToSelect, Population population);
}
