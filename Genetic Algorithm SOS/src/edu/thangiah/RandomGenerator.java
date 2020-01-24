package edu.thangiah;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Basic random number generator used for various tasks
 */



public class RandomGenerator extends Random
{
	
	public RandomGenerator()
	{
		setSeed(System.currentTimeMillis());
	}
	
	
	/**
	 * Get the next random 1 or 0
	 */
	
	public int nextBinaryInt()
	{
		return (int) Math.random() * 1;
	}
	
	
	/**
	 * Get the next random boolean value
	 */
	
	public boolean nextBoolean()
	{
		if(nextBinaryInt() == 1)	return true;
		else						return false;
	}
	
	
	/**
	 * Get the next random integer bewtween boundaries from lower to upper inclusive
	 * 
	 * @param lower
	 * @param upper
	 */
	
	public int nextIntBetween(int lower, int upper)
	{
		return ThreadLocalRandom.current().nextInt(lower, upper+1);
	}
	
	
	/**
	 * Get the next random double between 0 and 1
	 * 
	 */
	
	public double nextDouble()
	{
		return ThreadLocalRandom.current().nextDouble();
	}
	
	
	/**
	 * Get an array of non repeating random integers 
	 * 
	 * @param lower
	 * @param upper
	 * @param numValues
	 * @return result array
	 */
	
	public int[] nextNonRepeatingRandomIntArray(int lower, int upper, int numValues)
	{
		int [] tempArray = new int[upper]; 
		int [] result = new int[numValues];
		
		for(int i = lower; i < upper; i++)
		{
			tempArray[i] = i;
		}
		
		Random rgen = new Random();  // Random number generator			
		 
		for (int i = 0; i < tempArray.length; i++) 
		{
		    int randomPosition = rgen.nextInt(tempArray.length);
		    int temp = tempArray[i];
		    tempArray[i] = tempArray[randomPosition];
		    tempArray[randomPosition] = temp;
		}
		
		for(int i = 0; i < numValues; i++)
		{
			result[i] = tempArray[i];
		}
		
		return result;
	}
	
}