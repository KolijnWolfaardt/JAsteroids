package com.JAsteroids;

import org.lwjgl.opengl.GL11;

public class RenderEngine
{
	public static void Render (Cell cellToRender)
	{
		int xMax = (int) (cellToRender.playerX+600);
		int xMin = (int) (cellToRender.playerY-600);
		
		int yMax = (int) (cellToRender.playerY+400);
		int yMin = (int) (cellToRender.playerY-400);
		
		int offsetX = (int) (400-cellToRender.playerX);
		int offsetY = (int) (300-cellToRender.playerY);
		
		//Render Background
		
		//Render Sun
		cellToRender.theSun.render(offsetX,offsetY);
				
				
		//Render Enemies
		//Render Planets
		for (int i =0;i<cellToRender.numPlanets;i++)
		{
			cellToRender.planets[i].render(offsetX,offsetY);
		}
		
		
		
		//Render PLayer
		
		
		TextureManager.playership.bind();
		//Draw a quad in the centre of the screen
		GL11.glPushMatrix();
		GL11.glTranslatef(400, 300, 0);
		GL11.glRotatef((float) Math.toDegrees(cellToRender.rotation), 0f, 0f, 1f);
		GL11.glTranslatef(-400, -300, 0);
		
			GL11.glBegin(GL11.GL_QUADS);
				
				GL11.glTexCoord2f(0.0f,0.0f);
				GL11.glVertex2f(376, 276);
				
				GL11.glTexCoord2f(TextureManager.playership.getWidth(),0.0f);
				GL11.glVertex2f(424, 276);
				
				GL11.glTexCoord2f(TextureManager.playership.getWidth(),TextureManager.playership.getHeight());
				GL11.glVertex2f(424, 324);
				
				GL11.glTexCoord2f(0.0f,TextureManager.playership.getHeight());
				GL11.glVertex2f(376, 324);	
				
			GL11.glEnd();	
		GL11.glPopMatrix();
	}
}
