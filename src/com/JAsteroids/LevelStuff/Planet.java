package com.JAsteroids.LevelStuff;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.TextureManager;

public class Planet
{
	public int x;
	public int y;
	public int r;
	public int w = 150;
	public int h = 150;
	public int planetTexNum;
	public float weight = 10000.0f;
	public int orbitDistance = 700;
	public float orbitAngle = 0.0f;
	
	public Planet (int newX, int newY)
	{
		x = newX;
		y = newY;
		planetTexNum=0;
	}
	
	public void render(int xpos, int ypos)
	{
		TextureManager.planets[planetTexNum].bind();
			GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(x+xpos-w, y+ypos-h);
			
			GL11.glTexCoord2f(TextureManager.planets[planetTexNum].getWidth(),0.0f);
			GL11.glVertex2f(x+xpos+w, y+ypos-h);
			
			GL11.glTexCoord2f(TextureManager.planets[planetTexNum].getWidth(),TextureManager.planets[planetTexNum].getHeight());
			GL11.glVertex2f(x+xpos+w, y+ypos+h);
			
			GL11.glTexCoord2f(0.0f,TextureManager.planets[planetTexNum].getHeight());
			GL11.glVertex2f(x+xpos-w, y+ypos+h);	
		
		GL11.glEnd();
	}
}
