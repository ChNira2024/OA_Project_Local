package com.mashreq.oa.main;

public class ValueDifferenceExample 
{
    public static void main(String[] args) {
        int oldValue = 7000;
        int newValue = 10000;

        // Calculate the difference
        int difference = oldValue - newValue;

        // If you want the absolute value of the difference
        int absoluteDifference = Math.abs(difference);

        System.out.println("Difference: " + difference);
        System.out.println("Absolute Difference: " + absoluteDifference);
    }
}
