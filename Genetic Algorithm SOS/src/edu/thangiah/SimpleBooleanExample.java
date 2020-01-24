package edu.thangiah;

import java.util.LinkedList;

public class SimpleBooleanExample {
  /**
   * Starts the example.
   * @param args if optional first argument provided, it represents the number
   * of bits to use, but no more than 32
   */
	
  public static void main(String[] args) {
    int numEvolutions = 500;
    int chromeSize;
    double maxFitness;
    
    Configuration gaConf = new DefaultConfiguration();
    //gaConf.setPreservFittestIndividual(true);
    //gaConf.setKeepPopulationSizeConstant(false);
    
    Genotype genotype = null;
    
   
    if (args.length > 0) {
      gaConf.setChromosomeSize(Integer.parseInt(args[0]));
    }
    else {
    	gaConf.setChromosomeSize(16);
    }
    
    maxFitness = Math.pow(2.0, (double) gaConf.getChromosomeSize()) - 1;
    
    System.out.println("Max Fitness is " + maxFitness);
    
    
    if (gaConf.getChromosomeSize() > 32) {
      System.err.println("This example does not handle " +
                         "Chromosomes greater than 32 bits in length.");
      System.exit( -1);
    }
    try {
      LinkedList<IGene> sampleGenes = new LinkedList<IGene>();
      sampleGenes.add(new BinaryGene(gaConf));
      gaConf.setSampleGenes(sampleGenes);
      
      ISampleGeneChooser chooser = gaConf.getSampleGeneChooser();
      
      IChromosome sampleChromosome = new Chromosome(gaConf,
          chooser.choose(gaConf), gaConf.getChromosomeSize());
      gaConf.setSampleChromosome(sampleChromosome);
      gaConf.setPopulationSize(100);
      gaConf.setFitnessFunction(new MaxFunction());
      gaConf.addGeneticOperator(new StaticMutationOperator(gaConf));
      gaConf.addGeneticOperator(new BasicCrossoverOperator(gaConf));
      gaConf.addSelectionOperator(new RouletteSelectionOperator(gaConf));
      gaConf.setNumGenerations(numEvolutions);
      //gaConf.setMutationProbability(20);
      genotype = Genotype.randomInitialGenotype(gaConf);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit( -2);
    }
    
    
    int progress = 0;
    int percentEvolution = numEvolutions / 100;
    for (int i = 0; i < numEvolutions; i++) {
    	
      genotype.evolve();
      // Print progress.
      // ---------------
      if (percentEvolution > 0 && i % percentEvolution == 0) {
        progress++;
        IChromosome fittest = genotype.getFittestChromosome();
        double fitness = fittest.getFitnessValue();
        System.out.println("Currently fittest Chromosome has fitness " +
         fitness);
        
        
        if (fitness >= maxFitness) {
          break;
        }
      }
    }
    // Print summary.
    // --------------
    IChromosome fittest = genotype.getFittestChromosome();
    System.out.println("Fittest Chromosome has fitness " +
                       fittest.getFitnessValue());
  }
}
