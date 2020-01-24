package edu.thangiah;

public class TwoVariableMaxFunction extends AbstractFitnessFunction {

	/**
	 * Function that evaluates the fitness of a given chromosome
	 * 
	 * @param IChromosome chrome
	 */	
	
	  public double evaluate(IChromosome chrome) {
	    
		  double xAndY [] = {};
		  double x = 0.0, y = 0.0, result = 0.0, term1 = 0.0;
		  double e = Math.E;
		  xAndY = convertToDouble(chrome);
		  
		  x = (xAndY[0] * .0235294) - 3;
		  y = (xAndY[1] * .0235294) - 3;
		  
		 //term1 = (Math.pow(-x,2) - Math.pow((y+1), 2));
		  
		  result = (Math.pow((1-x), 2) * Math.pow(e, (Math.pow(-x,2) - Math.pow((y+1), 2)))) - 
				  (x - Math.pow(x, 3) - Math.pow(y, 3)) * Math.pow(e, (Math.pow(-x, 2) - Math.pow(y, 2)));
		 
		  //result = Math.pow((1-x), 2) * Math.pow(e, term1);
		  
		  return result;
		  
	  }
	  
	  protected double[] convertToDouble(IChromosome chrome)
	  {
		  double x = 0, y = 0;
		  double [] result = {-20, -20};
			
		    for (int i = 0; i < chrome.size()/2; i++) {
		      BinaryGene value = (BinaryGene) chrome.getGene(chrome.size() -
		          (i + 1));
		      
		      	x += Math.pow(2.0, (double) i) * (int)value.getInternalValue();
		    }
		    	
		    result[0] = x;
		    	
	    	for (int i = chrome.size()/2; i < chrome.size(); i++) {
			      BinaryGene value = (BinaryGene) chrome.getGene(chrome.size() -
			          (i + 1));
			      
			      	y += Math.pow(2.0, (double) (i-chrome.size()/2)) * (int)value.getInternalValue();
			}
	    	
	    	result[1] = y;
		 
	    	
	    	return result;
	  }
	  
}
