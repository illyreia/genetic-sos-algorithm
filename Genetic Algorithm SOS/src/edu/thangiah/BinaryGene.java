package edu.thangiah;

/**
 * A BinaryGene is a gene that has the integer value of either 1 or 0
 * 
 * @author Charlie Davis
 *
 */


public class BinaryGene extends AbstractGene
{
	public BinaryGene(final Configuration currentConfiguration)
	{
		super(currentConfiguration);
	}

	public BinaryGene(final Configuration currentConfiguration, final int currentValue)
	{
		super(currentConfiguration);
		setValue(currentValue);
		  
	}
	 
	
	/**
	 * Mutate this gene.  Flip the value from 0 to 1 or from 1 to 0
	 * 
	 */

	public void mutate()
	{
		if((int)getInternalValue() == 1)
		{
			setValue(0);
		}else
		{
			setValue(1);
		}
	}
	
	
	/**
	 * newGeneInternal is to be implemented by the class that extends 
	 * AbstractGene.  It is the responsibility of the child class to define what 
	 * a new gene is.  See Binary.java for example.
	 * 
	 * @return new IChromosome
	 */
	
	@Override
	protected IGene newGeneInternal() {
		return new BinaryGene(getConfiguration());
	}
	
	
	/**
	 * Get the internal value of this gene to be implement by the class that extends
	 * AbstractGene.  See BinaryGene.java for an example
	 * 
	 * @return value
	 */
	
	@Override
	public Object getInternalValue() {
		return (int)value;
	}
	
	
	/**
	 * Set this gene's value to a random value of the specific allele Object type
	 * 
	 * @param numberGenerator
	 */
	
	public void setToRandomValue(RandomGenerator numberGenerator) 
	{
		int random = numberGenerator.nextIntBetween(0,1);
		setValue(random);	
	}
	
}