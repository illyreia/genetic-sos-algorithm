package edu.thangiah;

import java.util.LinkedList;

public class DefaultConfiguration extends Configuration
{
	public DefaultConfiguration()
	{
		super();
		setPopulationSize(20);
		setNumGenerations(100);
		setMutationProbability(.001);
		setCrossoverProbability(.70);
		lockSettings();
		setRandomGenerator(new RandomGenerator());
		setSampleGeneChooser(new RandomSampleGeneChooser());
		setCurrentGenerationNum(0);
		setGeneticOperators(new LinkedList<IGeneticOperator>());
		setSelectionOperators(new LinkedList<ISelector>());
	}
}