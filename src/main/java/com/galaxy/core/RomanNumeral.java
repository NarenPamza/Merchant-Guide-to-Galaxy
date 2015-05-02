package com.galaxy.core;

/**
 * This RomanNumeral enum holds the roman values
 *
 */
public enum RomanNumeral
{
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

    int itsValue;

    RomanNumeral(int theValue)
    {
        this.itsValue = theValue;
    }

    public int getValue()
    {
        return itsValue;
    }
};
