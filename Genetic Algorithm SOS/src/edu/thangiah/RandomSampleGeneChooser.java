package edu.thangiah;

import java.util.LinkedList;

/**
 * RandomSampleGeneChooser chooses a random sample gene from a LinkedList 
 * 
 * @author Emily Van Horn
 *
 */

public class RandomSampleGeneChooser implements ISampleGeneChooser{

	@Override
	public IGene choose(Configuration config) {
		LinkedList<IGene> sampleGenes = config.getSampleGenes();
		RandomGenerator randomGenerator = config.getRandomGenerator();
		
		return sampleGenes.get(randomGenerator.nextInt() % sampleGenes.size());
	}

}
