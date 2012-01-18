// Kaas
package com.JAsteroids;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.*;
 
public class Runner
{
	Texture planet;
	 
	public void start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			GLInit();
			planet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/planet1.png"));
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	 
		// init OpenGL here
		 
		while (!Display.isCloseRequested())
		{ 
			// render OpenGL here
			render();
		}

		planet.release();
		Display.destroy();
	}
	
	public void render()
	{
	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		 
		GL11.glClearColor(0.0f,0.0f,0.0f,1.0f);
		
		//GL11.glPushMatrix();
		
		GL11.glColor4f(1.0f,0.3f,0.2f,1.0f);
		planet.bind();
		GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(250, 150);
			
			GL11.glTexCoord2f(planet.getWidth(),0.0f);
			GL11.glVertex2f(550, 150);
			
			GL11.glTexCoord2f(planet.getWidth(),planet.getHeight());
			GL11.glVertex2f(550, 450);
			
			GL11.glTexCoord2f(0.0f,planet.getHeight());
			GL11.glVertex2f(250, 450);	
			
		GL11.glEnd();	
		//GL11.glPopMatrix();
			

		Display.update();
		Display.sync(120);
	}
	
	public void GLInit()
	{
		
		//Set projection to the identity matrix
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
		//Set rendering to orth
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_BLEND); 
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	 
	public static void main(String[] argv)
	{
		Runner displayExample = new Runner();
		displayExample.start();
	}
}