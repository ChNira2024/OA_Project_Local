package com.mashreq.oa.main;

public class ValueDifferenceExample 
{
	
	public static Double decimalTruncate(Double value)
	{		
		return Math.floor(value*100)/100;	//Math.floor to round down to the nearest integer, and then divides by 100 
	}
    public static void main(String[] args) 
    {
        int oldValue = 7000;
        int newValue = 10000;

        // Calculate the difference
        int difference = oldValue - newValue;

        // If you want the absolute value of the difference
        int absoluteDifference = Math.abs(difference);

        System.out.println("Difference: " + difference);
        System.out.println("Absolute Difference: " + absoluteDifference);
        
        Double truncatedValue = decimalTruncate(123.456789);

        // Print the original and truncated values to the console
        System.out.println("Truncated Value: " + truncatedValue);
    }
}
