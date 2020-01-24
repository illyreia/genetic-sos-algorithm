package edu.thangiah;

/**
 * Simple fitness function that evaluates the fitness of a given chromosome
 * This particular fitness function test to see if the IChromosome sent to is 
 * the highest possible binary number.
 * 
 * @author Charlie Davis modified from JGAP
 *
 */

public class MaxFunction
    extends AbstractFitnessFunction {

	/**
	 * Function that evaluates the fitness of a given chromosome
	 * 
	 * @param IChromosome chrome
	 */	
	
	  public double evaluate(IChromosome chrome) {
	    int total = 0;
	
	    for (int i = 0; i < chrome.size(); i++) {
	      BinaryGene value = (BinaryGene) chrome.getGene(chrome.size() -
	          (i + 1));
	      
	      	total += Math.pow(2.0, (double) i) * (int)value.getInternalValue();
	    }
	
	    return total;
	  }
}
