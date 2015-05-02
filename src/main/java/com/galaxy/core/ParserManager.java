package com.galaxy.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import utils.ConstantValues;
import utils.StringUtils;

/**
 * This class is a singleton class. Parser Manager Manages parsing Inputs. This
 * class check whether input is assertion or questions. If it is assertions,
 * then the value get saved in AssignedValueMap of the {@link Dictionary}.
 */
public class ParserManager
{
    private ValueConverter itsConvertor;

    private Dictionary itsDictionary;

    /**
     * Get Instance Singleton
     * @return
     */
    public static ParserManager getInstance()
    {
        return new ParserManager();
    }

    private ParserManager()
    {
        itsConvertor = new ValueConverter();
        
        itsDictionary = Dictionary.getInstance();
    }

    /**
     * Parse Inputs
     * @throws Exception
     */
    public void parseInput() throws Exception
    {
    	String currentLine ;
    	try(BufferedReader br = new BufferedReader(new FileReader("SampleInput.txt")))
    	{
    		while ((currentLine = br.readLine()) != null)
    		{
    			// Get the User Input
    			if (StringUtils.isNotNullOrEmpty(currentLine) && currentLine.startsWith(ConstantValues.HOW))
    			{
    				parseQuestions(currentLine);
    			}
    			else
    			{
    				parseAssertion(currentLine);
    			}
    		}
    	}
    	catch(Exception theException)
        {
    		theException.printStackTrace();
        }
    }

	/**
	 * The Parse Question input will accept only the questions which starts with
	 * "how much" and "how many".
	 * 
	 * @param theInput
	 * @throws Exception
	 */
    private void parseQuestions(String theInput) throws Exception
    {
        if ((theInput.startsWith(ConstantValues.HOW_MUCH) || theInput.startsWith(ConstantValues.HOW_MANY)))
        {
            double aResult = itsConvertor.convertInputValues(theInput);
            if (aResult != -1 && aResult != 0)
            {
                System.out.println(aResult);
            }
            else
            {
                System.out.println("InValid Input! No Results");
            }
        }
    }

	/**
	 * Parse Assertion parse the input by splitting the input by " is " first
	 * and then will split the second part using space and then stored the newly
	 * assigned values to the Assigned value map of {@link Dictionary}
	 * 
	 */
    private void parseAssertion(String theInput)
    {
        if (StringUtils.isNotNullOrEmpty(theInput))
        {
            String[] aInputSplits = theInput.split(ConstantValues.IS_VALUE);
            
            if (aInputSplits.length > 1 && StringUtils.isNotNullOrEmpty(aInputSplits[1]))
            {
                if (aInputSplits[1].endsWith(ConstantValues.CREDITS))
                {
                    double aFinalCredits = 0;
                    String[] split = aInputSplits[0].split(ConstantValues.SPACE);
                    String aCreditName = null;
                    
                    List<Object> aRomanValueList = new ArrayList<Object>();
                    for (String aGalacticUnit : split)
                    {
                    	// Get the Assigned Value for the Galactic unit
                        String value = (String)itsDictionary.getAssignedValue(aGalacticUnit);
                        
                        // Get the Roman Numeral for the Galactic unit.
                        RomanNumeral romanNumerial = itsDictionary.getRomanNumerialValue(value);
                        if (romanNumerial != null)
                        {
                        	// Add Roman Numeral Name to the list
                        	aRomanValueList.add(romanNumerial.name());
                        }
                        else
                        {
                        	// Credit Name
                            aCreditName = aGalacticUnit;
                        }
                    }

                    String[] aCreditSplit = aInputSplits[1].split(ConstantValues.SPACE);
                    if (aCreditSplit != null)
                    {
                    	RomanConversion aRomanConversion = new RomanConversion();
                    	
                    	// Pass the Roman Values as a List to get the final Arabic value of the Roman values
                    	double anArabicValue = aRomanConversion.convertRomanNumerialToValue(aRomanValueList);
                    	
						/*
						 * We take only the First value of the Credit split
						 * Array. Since we assume that we will have only one
						 * value as a credit name. eg: Silver Credits. here we
						 * omit the Credits value and find the Credits for
						 * Silver
						 */
                        aFinalCredits = (Double.parseDouble(aCreditSplit[0]) / anArabicValue);
                    }
                    
                    // Add the Credit Name as key and Credit Value as Value
                    itsDictionary.addAssignedValue(aCreditName, aFinalCredits);
                }
                else
                {
                	// Add the Galactic name as key and assigned Roman value of it as value
                	itsDictionary.addAssignedValue(aInputSplits[0].trim(), aInputSplits[1].trim());
                }
            }
            else
            {
            	System.out.println("Invalid Question!");
            	System.out.println("Please find the below Sample Input. All the values are case senstive");
            	System.out.println("glob is I");
            	System.out.println("glob is I");
            	System.out.println("how much is glob glob ?");
            }
        }
    }
}
