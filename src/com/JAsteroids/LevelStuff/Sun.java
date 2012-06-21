package com.JAsteroids.LevelStuff;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.TextureManager;

public class Sun
{
	public int x;
	public int y;
	public int r;
	public int w = 300; //give the half of the width
	public int h = 300; //give the half of the height
	public int sunTexNum;
	public float weight = 20000.0f;
	
	public Sun (int newX, int newY)
	{
		x = newX;
		y = newY;
		sunTexNum=0;
	}
	
	public void render(int xpos, int ypos)
	{
		TextureManager.suns[sunTexNum].bind();
			GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f((x-xpos)-w, (y-ypos)-h);
			
			GL11.glTexCoord2f(TextureManager.suns[sunTexNum].getWidth(),0.0f);
			GL11.glVertex2f((x-xpos)+w, (y-ypos)-h);
			
			GL11.glTexCoord2f(TextureManager.suns[sunTexNum].getWidth(),TextureManager.suns[sunTexNum].getHeight());
			GL11.glVertex2f(x-xpos+w, (y-ypos)+h);
			
			GL11.glTexCoord2f(0.0f,TextureManager.suns[sunTexNum].getHeight());
			GL11.glVertex2f((x-xpos)-w,(y-ypos)+h);	
		
		GL11.glEnd();
	}
}
