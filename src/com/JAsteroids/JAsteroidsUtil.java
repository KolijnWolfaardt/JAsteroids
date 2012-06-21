package com.JAsteroids;

public class JAsteroidsUtil
{
	public static float distance(float a, float b)
	{
		return (float) Math.sqrt(a*a + b*b);
	}
	
	public static int distance (int a, int b)
	{
		return (int) Math.sqrt(a*a + b*b);
	}
	
	public static float max(float arr[])
	{
		float max = arr[0];
		
		for (int i = 0;i<arr.length;i++)
		{
			if (arr[i] > max)
			{
				max = arr[i];
			}
		}
		
		return max;
	}
	
	public static float min(float arr[])
	{
		float min = arr[0];
		
		for (int i = 0;i<arr.length;i++)
		{
			if (arr[i] < min)
			{
				min = arr[i];
			}
		}
		
		return min;
	}

}
