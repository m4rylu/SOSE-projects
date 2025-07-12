package it.sose.data.analysis;

import java.io.Serializable;

/**
 * The data class holds the results of a basic statistical analysis.
 * It includes four integer fields representing:
 * <ul>
 *   <li>{@code mean} - the arithmetic average</li>
 *   <li>{@code sum} - the total sum of values</li>
 *   <li>{@code min} - the minimum value</li>
 *   <li>{@code max} - the maximum value</li>
 * </ul>
 * 
 * This class implements Serializable to allow instances to be easily stored.
 */
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	private int mean;
	private int sum;
	private int max;
	private int min;
	
    /** @return the calculated mean value */
	public int getMean() {
		return mean;
	}
	
    /** @return the total sum of values */
	public int getSum() {
		return sum;
	}
	
    /** @return the minimum value in the dataset */
	public int getMin() {
		return min;
	}
	
    /** @return the maximum value in the dataset */
	public int getMax() {
		return max;
	}
	
	/** @param mean the mean value to set */
	public void setMean(int mean) {
		this.mean=mean;
	}
	
    /** @param sum the total sum to set */
	public void setSum(int sum) {
		this.sum=sum;
	}
	
    /** @param min the minimum value to set */
	public void setMin(int min) {
		this.min=min;
	}
	
    /** @param max the maximum value to set */
	public void setMax(int max) {
		this.max=max;
	}

}
