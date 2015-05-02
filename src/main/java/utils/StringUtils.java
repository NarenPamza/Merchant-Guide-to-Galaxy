package utils;

/**
 * StringUtils method validate String related logic
 */
public class StringUtils 
{
	
	public static boolean isNotNullOrEmpty(String theValue)
	{
		if(!theValue.isEmpty() && theValue != null)
		{
			return true;
		}
		return false;
	}
}
