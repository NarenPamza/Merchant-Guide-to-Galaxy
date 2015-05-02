package com.galaxy.core;

import java.util.HashMap;
import java.util.Map;

/**
 * The Dictionary class is a singleton class, This class maintains the assigned
 * values in the Assigned value map and the Roman value in the Roman Numerials
 * Map.
 *
 */
public class Dictionary
{
    public Map<String, RomanNumeral> itsRomanNumerialsMap;

    public Map<String, Object> itsAssignedValueMap;
    
    private static Dictionary itsDictionary = new Dictionary();
    
    public static Dictionary getInstance()
    {
    	return itsDictionary;
    }

    private Dictionary()
    {
        itsRomanNumerialsMap = new HashMap<String, RomanNumeral>();
        itsAssignedValueMap = new HashMap<String, Object>();
        loadRomanNumerials();
    }

    private void loadRomanNumerials()
    {
        RomanNumeral[] romanNumerials = RomanNumeral.values();
        for (RomanNumeral romanNumerial : romanNumerials)
        {
            itsRomanNumerialsMap.put(romanNumerial.name(), romanNumerial);
        }
    }

    public void addAssignedValue(String theKey, Object theValue)
    {
        itsAssignedValueMap.put(theKey, theValue);
    }

    public Object getAssignedValue(String theKey)
    {
        return itsAssignedValueMap.get(theKey);
    }

    public RomanNumeral getRomanNumerialValue(String theKey)
    {
        return itsRomanNumerialsMap.get(theKey);
    }
}
