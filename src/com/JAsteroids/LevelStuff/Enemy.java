package com.JAsteroids.LevelStuff;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.TextureManager;

public class Enemy
{
	int textureID = 0;
	public float enemyX;
	public float enemyY;
	
	public float rotX = 1;
	public float rotY = 0;
	
	public float goalRotationX = 0.0f;
	public float goalRotationY = 1.0f;
	
	int targetX = 100;
	int targetY = 100;
	int w = 24;
	int h = 24;
	
	public void render(int xpos, int ypos)
	{
		TextureManager.enemies[textureID].bind();
		//Draw a quad in the centre of the screen
		
		GL11.glBegin(GL11.GL_QUADS);
				
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(enemyX+xpos-w, enemyY+ypos-h);
				
			GL11.glTexCoord2f(TextureManager.enemies[textureID].getWidth(),0.0f);
			GL11.glVertex2f(enemyX+xpos+w, enemyY+ypos-h);
				
			GL11.glTexCoord2f(TextureManager.enemies[textureID].getWidth(),TextureManager.enemies[textureID].getHeight());
			GL11.glVertex2f(enemyX+xpos+w, enemyY+ypos+h);
				
			GL11.glTexCoord2f(0.0f,TextureManager.enemies[textureID].getHeight());
			GL11.glVertex2f(enemyX+xpos-w, enemyY+ypos+h);
				
		GL11.glEnd();	
	}
}
