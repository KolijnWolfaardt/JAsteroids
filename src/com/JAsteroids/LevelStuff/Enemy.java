package com.JAsteroids.LevelStuff;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.JAsteroidsUtil;
import com.JAsteroids.TextureManager;
import com.JAsteroids.Vector2f;

public class Enemy extends MoveableObject
{
	int textureID = 0;
	
	public float rotX = 1;
	public float rotY = 0;
	
	public float goalRotationX = 0.0f;
	public float goalRotationY = 1.0f;
	
	int targetX = 100;
	int targetY = 100;
	
	
	public Enemy()
	{
		collisionPoints = new Vector2f[4];	//Actually in the form of a triangle
		collisionPoints[0] = new Vector2f(-19,-22);
		collisionPoints[1] = new Vector2f(18,-22);
		collisionPoints[2] = new Vector2f(18,20);
		collisionPoints[3] = new Vector2f(-19,20);
	}
	
	public int update()
	{
		return 0;
	}
	
	public void render(int xpos, int ypos)
	{
		TextureManager.enemies[textureID].bind();
		//Draw a quad in the center of the screen
		
		GL11.glBegin(GL11.GL_QUADS);
				
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f((X-xpos)-width, (Y-ypos)-height);
				
			GL11.glTexCoord2f(TextureManager.enemies[textureID].getWidth(),0.0f);
			GL11.glVertex2f((X-xpos)+width, (Y-ypos)-height);
				
			GL11.glTexCoord2f(TextureManager.enemies[textureID].getWidth(),TextureManager.enemies[textureID].getHeight());
			GL11.glVertex2f((X-xpos)+width, (Y-ypos)+height);
				
			GL11.glTexCoord2f(0.0f,TextureManager.enemies[textureID].getHeight());
			GL11.glVertex2f((X-xpos)-width,(Y-ypos)+height);
				
		GL11.glEnd();	
	}
}
