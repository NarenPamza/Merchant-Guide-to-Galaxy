package com.galaxy.core;

import java.util.List;
 
/**
 * 
 * Value Converter does two Process, First it converts the Galactic units to
 * Roman values using {@link IntergalacticConversion}, then it converts the
 * Roman values to Arabic values using {@link RomanConversion}
 *
 */
public class ValueConverter 
{
    public ValueConverter()
    {
    }

    public double convertInputValues(String theInput) throws Exception
    {
		double itsResult = 0;

		IntergalacticConversion anIntergalacticConversion = new IntergalacticConversion();
		List<Object> aRomanValueList = anIntergalacticConversion.convertIntergalacticToRoman(theInput);
		
		if(aRomanValueList == null)
		{
			return itsResult;
		}
		
		RomanConversion aRomanConvertor = new RomanConversion();
		itsResult = aRomanConvertor.convertRomanNumerialToValue(aRomanValueList);
				
		return itsResult;
	}
}
