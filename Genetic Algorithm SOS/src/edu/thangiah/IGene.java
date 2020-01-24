package edu.thangiah;

/**	
 * Gene interface class
 */


public interface IGene
{
	
	
	/**
	 * Returns a new instance of the specific type of this gene
	 * 
	 * @return new IGene
	 */
	
	public IGene newGene();
	
	
	/**
	 * Set the allele of this gene with a specific value.  
	 * 
	 * @param value
	 */
	
	public void setAllele(Object value);
	
	
	/**
	 * Get the allele object within this gene
	 * 
	 * @return Object allele
	 */
	
	public Object getAllele();
	
	
	/**
	 * Set this gene's value to a random value of the specific allele Object type
	 * 
	 * @param numberGenerator
	 */
	
	public void setToRandomValue(RandomGenerator numberGenerator);
	
	
	/**
	 * Returns the string representation of this gene
	 * 
	 * @return
	 */
	
	public String toString();
	
	
	/**
	 * Get the size of the gene
	 * 
	 * @return
	 */
	
	public int size();
	
	
	/**
	 * Set the configuration of this gene
	 * 
	 * @param currentConfiguration
	 */
	
	public void setConfiguration(Configuration currentConfiguration);
	
	
	/**
	 * Get the configuration of this gene
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration();
	
	
	/**
	 * Mutate this gene.  See Binary gene for an example.
	 * 
	 */

	public void mutate();
	
	
	/**
	 * Get the internal value of this gene
	 * 
	 * @return value
	 */

	public Object getInternalValue();
	
}