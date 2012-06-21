package com.JAsteroids.LevelStuff;

import com.JAsteroids.Vector2f;

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
		
		//Set collision points
		collisionPoints = new Vector2f[3];	//Actually in the form of a triangle
		collisionPoints[0] = new Vector2f(-24,-24);
		collisionPoints[1] = new Vector2f(24,0);
		collisionPoints[2] = new Vector2f(-24,24);
	}
	
	public int update()
	{
		return 0;
	}
	
	public void render(int xpos, int ypos)
	{
		
	}
	
}
