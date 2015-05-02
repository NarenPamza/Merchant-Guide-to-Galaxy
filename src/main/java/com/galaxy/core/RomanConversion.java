package com.galaxy.core;

import java.util.List;

/**
 * This class converts the Roman Values to Arabic Values. This class gets the
 * List of Roman Values and convert it to Arabic Values. This class find the
 * Credits values also for the Roman Values.
 *
 */
public class RomanConversion 
{
	
	private Dictionary itsDictionary;
	
	public RomanConversion()
	{
		itsDictionary = Dictionary.getInstance();
	}
	
	public double convertRomanNumerialToValue(List<Object> aRomanValueList)
	{
		RomanNumeral aTempValue = null;
		
		double aResult = 0;
		
		double aCreditValue = 0;
		
		if (aRomanValueList != null && aRomanValueList.size() >= 1) 
		{
			if (aRomanValueList.get(0) instanceof String) 
			{
				String aFirstValue = (String) aRomanValueList.get(0);
				RomanNumeral romanNumerial = getRomanNumerialValue(aFirstValue);
				aTempValue = romanNumerial;
				
				if(aRomanValueList.size() == 1)
				{
					aResult += aTempValue.getValue();
					return aResult;
				}
			}
			else if(aRomanValueList.get(0) instanceof Double)
			{
				aCreditValue += (Double)aRomanValueList.get(0);
			}
		}
		
		int count =0;

		for (int i = 1; i < aRomanValueList.size() ; i++) 
		{
			Object aFirstObjectValue = aRomanValueList.get(i);
			if (aFirstObjectValue instanceof String) 
			{
				String aFirstValue = (String) aFirstObjectValue;
				RomanNumeral aSecondNumerial = getRomanNumerialValue(aFirstValue);

				if (aSecondNumerial == null) 
				{
					aResult += aTempValue.getValue();
					++count;
				}

				// Add Temp Value to the Result
				if (aTempValue.getValue() >= aSecondNumerial.getValue()) 
				{
					aResult += aTempValue.getValue();
					++count;
					aTempValue = aSecondNumerial;
				} 
				else 
				{
					if(!isSubtractable(aSecondNumerial.name(), aTempValue.name()))
					{
						return 0;
					}
					
					aResult += (aSecondNumerial.getValue() - aTempValue.getValue());

					// Increment i
					++i;
					
					count = count + 2;
					
					if(i < aRomanValueList.size())
					{
						if(aRomanValueList.get(i) instanceof String)
						{
							String aNewTempElement = (String) aRomanValueList.get(i);
							RomanNumeral aNewTempNumerial = getRomanNumerialValue(aNewTempElement);
							aTempValue = aNewTempNumerial;
						}
						else if(aRomanValueList.get(i) instanceof Double)
						{
							aCreditValue += (Double)aRomanValueList.get(i);
							++count;
						}
					}
				}
			}
			else if(aRomanValueList.get(i) instanceof Double)
			{
				aCreditValue += (Double)aRomanValueList.get(i);
				++count;
			}
		}
		
		//Verify count to Add the last temp value to the Result
		if(count != aRomanValueList.size())
		{
			aResult += aTempValue.getValue();
		}
		
		if(aCreditValue != 0)
		{
			aResult = aResult * aCreditValue;
		}
		
		return aResult;
	}
	
	/**
	 * This method verify the following logic."I" can be subtracted from "V" and
	 * "X" only. "X" can be subtracted from "L" and "C" only. "C" can be
	 * subtracted from "D" and "M" only. "V", "L", and "D" can never be
	 * subtracted.
	 * 
	 * @param fromValue
	 * @param toValue
	 * @return
	 */
	private Boolean isSubtractable(String fromValue, String toValue) 
	{
		if (RomanNumeral.V.name().equalsIgnoreCase(toValue)
				|| RomanNumeral.L.name().equalsIgnoreCase(toValue)
				|| RomanNumeral.D.name().equalsIgnoreCase(toValue)) 
		{
			System.out.println("V, L and D can never be Subtracted!!");
			return false;
		}

		switch (toValue) 
		{

		case "I":
			if (fromValue.equalsIgnoreCase(RomanNumeral.V.name())
					|| fromValue.equalsIgnoreCase(RomanNumeral.X.name())) 
			{
				return true;
			} 
			else
			{
				System.out.println("I cannot be subtracted from '" + fromValue
						+ "' .'I' can be subtracted from 'V' and 'X' only ");
				return false;
			}
		case "X":
			if (fromValue.equalsIgnoreCase(RomanNumeral.L.name())
					|| fromValue.equalsIgnoreCase(RomanNumeral.C.name())) 
			{
				return true;
			} 
			else 
			{
				System.out.println("'X' cannot be subtracted from '" + fromValue
						+ "' .'X' can be subtracted from 'L' and 'C' only ");
				return false;
			}
		case "C":
			if (fromValue.equalsIgnoreCase(RomanNumeral.D.name())
					|| fromValue.equalsIgnoreCase(RomanNumeral.M.name())) 
			{
				return true;
			} 
			else 
			{
				System.out.println("'C' cannot be subtracted from '" + fromValue
						+ "' .'C' can be subtracted from 'D' and 'M' only ");
				return false;
			}
		}
		return true;
	}
	
	public RomanNumeral getRomanNumerialValue(String theKey) 
	{
		return itsDictionary.getRomanNumerialValue(theKey);
	}
}
