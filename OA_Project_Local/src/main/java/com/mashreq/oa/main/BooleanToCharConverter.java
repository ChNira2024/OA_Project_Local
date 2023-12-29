package com.mashreq.oa.main;
public class BooleanToCharConverter 
{
    public static char convertBooleanToChar(boolean value)
    {
        return value ? 'Y' : 'N';
    }

    public static void main(String[] args)
    {
        boolean trueValue = true;
        boolean falseValue = false;

        char trueChar = convertBooleanToChar(trueValue);
        char falseChar = convertBooleanToChar(falseValue);

        System.out.println("true as char: " + trueChar);
        System.out.println("false as char: " + falseChar);
    }
}
