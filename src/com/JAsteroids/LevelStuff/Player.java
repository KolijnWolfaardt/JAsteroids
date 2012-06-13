package com.JAsteroids.LevelStuff;

public class Player extends MoveableObject
{

	public float goalRotationX = 0.0f;	//goalRotation is used to make the ship seem a bit sluggish
	public float goalRotationY = 0.0f;
	
	public int boosterLeft = 100;
	public int boosterMax = 100;
	
	public int gunRecharge = 10;
	public int gunType = 0;
	
	public Player()
	{
		super();
	}
	
	public int update()
	{
		return 0;
	}
	
	public void render(int xpos, int ypos)
	{
		
	}
	
}
