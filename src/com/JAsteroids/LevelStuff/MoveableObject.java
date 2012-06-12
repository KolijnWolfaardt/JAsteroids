package com.JAsteroids.LevelStuff;


/**
 * This class is the parent of all movable items in the game. Background textures are not included here.
 * It servers as a base for collision checking, e.t.c
 * 
 * 
 * 
 * @author Kolijn
 *
 */
public class MoveableObject
{
	
	/**
	 * Known implementations:
	 * 	Bullet
	 * 	Enemy
	 */
	
	public float BulletX;
	public float BulletY;
	public float rotX = 1.0f;
	public float rotY = 0.0f;
	public float speedX = 0;
	public float speedY = 0;
	
	public MoveableObject()
	{
	
	}
	
	/**
	 * Render the object
	 * 
	 * @param xpos
	 * @param ypos
	 */
	public void render(int xpos, int ypos)
	{
		
	}
	
	public int update()
	{
		return 0;
	}
	
}
