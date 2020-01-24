package edu.thangiah;

import java.lang.reflect.Constructor;
import java.util.LinkedList;

public class Chromosome extends AbstractChromosome
{
	public Chromosome(final Configuration a_configuration, final int chromosomeSize)
	{
		super(a_configuration);
		if (chromosomeSize <= 0) 
		{
			throw new IllegalArgumentException("Chromosome size must be greater than zero");
		}
	}
	
	
	public Chromosome(final Configuration a_configuration,
            final IGene a_sampleGene, final int chromosomeSize)
	{	
		this(a_configuration, chromosomeSize);
		for(int i = 0; i< chromosomeSize; i++)
		{
			addGene(null);
		}
		
		initFromGene(a_sampleGene);
	}
	
	/**
	 * Protected function used by Constructor to make new instances of this chromosome
	 * The sampleGene is the type of gene that is currently stored in this chromosome.
	 * 
	 * @param sampleGene
	 */
	
	protected void initFromGene(IGene sampleGene) {
	    // Do sanity checking to make sure the parameters we were
	    // given are valid.
	    // ------------------------------------------------------
	    if (sampleGene == null) {
	      throw new IllegalArgumentException(
	          "Sample Gene cannot be null.");
	    }
	    // Populate the array of genes it with new Gene instances
	    // created from the sample gene.
	    // ------------------------------------------------------
	    int size = getConfiguration().getChromosomeSize();
	    for (int i = 0; i < size; i++) {
	    	
	      setGene(i, sampleGene.newGene());
	    }
	  }
	
	
	@Override
	public int getValue() {
		return 0;
	}


	/**
	 * Sets the genes of this chromosome to random values of their instanciated type
	 * 
	 * @param generator
	 */
	
	@Override
	public void setRandomGenes(RandomGenerator generator) {
	
		for(int i = 0; i < this.size(); i++)
		{
			this.getGenes().get(i).setToRandomValue(generator);
		}	
		
	}
	
	
	/**
	 * newChromosomeInternal used by abstract newChromosome to make a new
	 * instance of this chromosome.
	 * 
	 * @return new IChromosome
	 */
	
	public Chromosome newChromosomeInternal(){
		Configuration config = getConfiguration();
		IGene sampleGene = getConfiguration().getSampleGeneChooser().choose(config);
		int size = config.getChromosomeSize();
		
		Chromosome chrome = new Chromosome(config, sampleGene, size);
		
		return chrome;

	}
	
	
}