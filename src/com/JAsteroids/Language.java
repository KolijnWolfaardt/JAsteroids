package com.JAsteroids;

public class Language
{

	String[] currentLanguage = {"Java Asteroids","New Game","Options","Exit Game"};
	
	public Language()
	{
		
	}
	
	public String getString(int num)
	{
		if (num<currentLanguage.length)
			return currentLanguage[num];
		else
			return null;
	}
}
