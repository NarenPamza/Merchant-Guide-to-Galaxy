package com.galaxy.core;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import utils.ConstantValues;

/**
 * This Class Converts the Galactic values to Roman Values.This class get
 * the user input as a Galactic value and convert it to the Roman values
 *
 */
public class IntergalacticConversion 
{
	private Dictionary itsDictionary;
	
	public IntergalacticConversion()
	{
		itsDictionary = Dictionary.getInstance();
	}

	public List<Object> convertIntergalacticToRoman(String theInputValue)
	{
		if(!theInputValue.contains(ConstantValues.QUESTION_MARK))
		{
			System.out.println("Invalid Question. ' ?' is missing from question !!");
		}
		
		theInputValue = theInputValue.trim().substring(0, theInputValue.indexOf('?'));

        String[] strings = theInputValue.split(ConstantValues.IS_VALUE);
        
        List<Object> aRomanValues = new ArrayList<Object>();

        if (strings.length > 1)
        {
            String string = strings[1];
            if (!string.isEmpty())
            {
            	System.out.println(string + " is ");
                // Convert to Char Array to check for ConsecutiveNumbers
                StringTokenizer aTokenizer = new StringTokenizer(string, ConstantValues.SPACE);
                while (aTokenizer.hasMoreElements())
                {
                	String anIntergalacticName = (String)aTokenizer.nextElement();
                	
                	// Get the Assigned Value for the Intergalactic name
                	Object anAssignedValue = itsDictionary.getAssignedValue(anIntergalacticName);
                	if(anAssignedValue != null)
                	{
                		aRomanValues.add(anAssignedValue);
                	}
                }
                
                if(aRomanValues.isEmpty())
                {
                	System.out.println("Invalid Galatic Values.Please try again with the assigned galactic values");
                	return null;
                }
                
                if(isConsecutive(aRomanValues))
                {
                	System.out.println("Consective numbers are not allowed");
                	return null;
                }
            }
        }
		return aRomanValues;
	}
	
	/**
	 * Check for the Consecutive values. The symbols "I", "X", "C", and "M" can
	 * be repeated three times in succession, but no more. (They may appear four
	 * times if the third and fourth are separated by a smaller value, such as
	 * XXXIX.) "D", "L", and "V" can never be repeated.
	 * 
	 * @param a
	 * @return
	 */
	private boolean isConsecutive(List<Object> a)
    {
        char temp = 0;
        
        if( a.get(0) instanceof String)
        {
        	String aStringValue = (String) a.get(0);
        	temp = aStringValue.charAt(0);
        }
        
        int count = 1;
        for (int i = 1; i < a.size(); i++)
        {
        	if(a.get(i) instanceof String)
        	{
        		String aStringValue = (String) a.get(i);
        		
        		char aValue = aStringValue.charAt(0);
        		
        		if (aValue != temp)
        		{
        			temp = aValue;
        			count = 1;
        		}
        		else if (aValue == temp)
        		{
        			count++;
        		}
        		
        		if (count == 2 && (aValue == 'D' || aValue == 'L' || aValue == 'V'))
        		{
        			System.out.println("Consective Numbers of "+ a.get(i) + " are not allowed");
        			return true;
        		}
        		
        		if (count == 4)
        		{
        			System.out.println("Consective Numbers of "+ a.get(i) + " are not allowed");
        			return true;
        		}
        	}
        }
        return false;
    }
}
