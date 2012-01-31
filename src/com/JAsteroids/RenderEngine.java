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
		int pos1= 255- (int) (cellToRender.playerX%255);
		int pos2= 255- (int) (cellToRender.playerY%255);
		
		for (int i = -3;i<3;i++)
		{
			for (int j = -3;j<3;j++)
			{	
				RenderBackground(cellToRender.backgroundNum,pos1+i*255,pos2+j*255);
			}
		}
		
		//Render Sun
		cellToRender.theSun.render(offsetX,offsetY);
				
				
		//Render Enemies
		for (int i = 0; i<cellToRender.numEnemies; i++)
		{
			cellToRender.enemies[i].render(offsetX,offsetY);
		}
		
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
		GL11.glRotatef((float) Math.toDegrees(Math.atan2(cellToRender.rotX,cellToRender.rotY)), 0f, 0f, 1f);
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
	
	public static void RenderBackground(int num, int xpos, int ypos)
	{
		TextureManager.backgrounds[num].bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex2f(xpos, ypos);
		
		GL11.glTexCoord2f(TextureManager.backgrounds[num].getWidth(),0.0f);
		GL11.glVertex2f(xpos+255, ypos);
		
		GL11.glTexCoord2f(TextureManager.backgrounds[num].getWidth(),TextureManager.backgrounds[num].getHeight());
		GL11.glVertex2f(xpos+255, ypos+255);
		
		GL11.glTexCoord2f(0.0f,TextureManager.backgrounds[num].getHeight());
		GL11.glVertex2f(xpos, ypos+255);	
	
	GL11.glEnd();
	}
}
