package edu.thangiah;

/**
 * A SampleGeneChooser is an interface class that helps a chromosome figure out what type
 * of gene it is for creating new instances of Chromosomes
 * 
 * @author Emily Van Horn
 *
 */

public interface ISampleGeneChooser {
	public IGene choose(Configuration config);
}
