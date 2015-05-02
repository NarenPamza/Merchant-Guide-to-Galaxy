package com.galaxy.core;

/**
 * This is the Main Entry Point for this Application. This Class initialize
 * ParserManager. The Parser Manager get the Input from the user and parse the
 * input to get the Output.
 * 
 * The Input should be separated by Space properly. The Term Credits used in the input is Case-Sensitive.
 * All the Value in the inputs are case-sensitive.
 * Sample Inputs : 
 * glob is I 
 * prok is V 
 * pish is X 
 * tegj is L 
 * glob glob Silver is 34 Credits
 * glob prok Gold is 57800 Credits 
 * pish pish Iron is 3910 Credits
 *
 */
public class SampleMain
{
    public static void main(String[] args)
    {
        try
        {
            ParserManager aConvertor = ParserManager.getInstance();
            aConvertor.parseInput();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
