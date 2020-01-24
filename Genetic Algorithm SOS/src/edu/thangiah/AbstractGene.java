package edu.thangiah;

/**
 * AbstractGene is a the abstract version of a basic IGene
 * 
 * @author Charlie Davis 
 *
 */


public abstract class AbstractGene implements IGene
{
	Object value;
	private Configuration configuration;
	
	public AbstractGene(Configuration currentConfiguration)
	{
		 setConfiguration(currentConfiguration);
	}
	
	
	/**
	 * Set the configuration of this gene
	 * 
	 * @param currentConfiguration
	 */
	
	public void setConfiguration(Configuration currentConfiguration){ configuration = currentConfiguration; }
	
	
	/**
	 * Get the configuration of this gene
	 * 
	 * @return configuration
	 */
	
	public Configuration getConfiguration(){ return configuration; }
	
	
	/**
	 * Set the allele of this gene with a specific value.  
	 * 
	 * @param value
	 */
	
	
	public void setAllele(Object value){ this.value = value; }
	
	
	/**
	 * Get the allele object within this gene
	 * 
	 * @return Object allele
	 */
	
	public Object getAllele(){ return value; }
	
	
	/**
	 * Set this gene's value to a random value of the specific allele Object type
	 * 
	 * @param numberGenerator
	 */
	
	public void setToRandomValue(RandomGenerator numberGenerator){}
	
	
	/**
	 * Returns a new instance of the specific type of this gene
	 * 
	 * @return new IGene
	 */
	
	public IGene newGene()
	{ 
		IGene result = newGeneInternal();
		return result;
	}
	
	
	/**
	 * newGeneInternal is to be implemented by the class that extends 
	 * AbstractGene.  It is the responsibility of the child class to define what 
	 * a new gene is.  See Binary.java for example.
	 * 
	 * @return new IChromosome
	 */
	
	protected abstract IGene newGeneInternal();
	
	
	/**
	 * Get the internal value of this gene to be implement by the class that extends
	 * AbstractGene.  See BinaryGene.java for an example
	 * 
	 * @return value
	 */
	
	public abstract Object getInternalValue();
	
	
	/**
	 * Set the allele of this gene with a specific value.  
	 * 
	 * @param value
	 */
	
	public void setValue(Object currentValue)
	{
		setAllele(currentValue);
	}
	
	
	/**
	 * Returns the string representation of this gene
	 * 
	 * @return
	 */
	
	public String toString()
	{
		String representation;
	    if (getInternalValue() == null) 
	    {
	      representation = "null";
	    }
	    else 
	    {
	      representation = getInternalValue().toString();
	    }
	    return representation;
	}
	
	
	public int size(){ return 1;}
		
}