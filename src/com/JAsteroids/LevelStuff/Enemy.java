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
	
	public float maxSpeed = 2.0f;
	
	float targetX = 100;
	float targetY = 100;
	
	
	public Enemy()
	{
		//
	}
	
	public int update()
	{
		//Shall we go to a new destination?
		if (Math.random() < 0.005) //0.5% chance
		{
			do
			{
				targetX = (float) (targetX+(Math.random()-0.5)*1000);
			}
			while (targetX > 2000 | targetX<-2000);
			do
			{
				targetY = (float) (targetY+(Math.random()-0.5)*1000);
			}
			while (targetY > 2000 | targetY <-2000);
		}
		
		float targetRotX = X-targetX;
		float targetRotY = Y-targetY;
		float size = JAsteroidsUtil.distance(targetRotX, targetRotY);
		targetRotX = targetRotX/size;
		targetRotY = targetRotY/size;
		
		X = X+speedX;
		Y = Y+speedY;
		
		if (JAsteroidsUtil.distance(X-targetX,Y-targetY) > 10)
		{
			//Move
			speedX-= (X-targetX)/10000.0f;
			if (speedX>maxSpeed)
				speedX=maxSpeed;
			if (speedX<-maxSpeed)
				speedX=-maxSpeed;
			
			
			speedY-= (Y-targetY)/10000.0f;
			if (speedY>maxSpeed)
				speedY=maxSpeed;
			if (speedY<-maxSpeed)
				speedY=-maxSpeed;
		}
		
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
	
	public static Enemy newEnemy(int enemyType)
	{
		Enemy newEnemy = new Enemy();
		
		switch (enemyType)
		{
		case 0:
			newEnemy.collisionPoints = new Vector2f[4];
			newEnemy.collisionPoints[0] = new Vector2f(-19,-22);
			newEnemy.collisionPoints[1] = new Vector2f(18,-22);
			newEnemy.collisionPoints[2] = new Vector2f(18,20);
			newEnemy.collisionPoints[3] = new Vector2f(-19,20);
			
			newEnemy.width = 24;
			newEnemy.height = 24;
			newEnemy.r = 35;
			
			newEnemy.X = 0;
			newEnemy.Y = 0;
		
			newEnemy.health = 30;
			break;
			
		}
		
		return newEnemy; 
	}
}
