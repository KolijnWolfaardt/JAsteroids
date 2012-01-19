package com.JAsteroids;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

 
public class Runner
{
	//Texture planet;
	int gameState = 1;
	Language stringLookup = new Language();
	Settings currentSettings;
	 
	public void start()
	{
		//TODO : Read Langauge from file
		currentSettings = new Settings();
		currentSettings.readFromFile("options.txt");
		
		try
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			GLInit();
		}
		catch (LWJGLException e)
		{
			System.out.println(stringLookup.getString(1));
			e.printStackTrace();
			System.exit(0);
		}
	 
		MenuClass atTheMenu = new MenuClass(currentSettings,stringLookup);
		LevelClass atTheLevel =	null;
		int newGameState = 1;
		
		
		while (!Display.isCloseRequested() && gameState != 0)
		{ 
			GL11.glClearColor(0.0f,0.0f,0.0f,1.0f);
			switch(gameState)
			{
				case 1:  newGameState = atTheMenu.render();	break;
				case 5: newGameState = atTheLevel.render(); break;
				//2 - settings
				//3 - reserved
				//4 - reserved
				//5 - game
			}
			
			switch (newGameState)
			{
			//0 means no change
				case 5: 
					atTheMenu.endClass(); 
					atTheMenu = null; 
					atTheLevel=new LevelClass();
				break;
			}
			if (newGameState != 0)
			{
				gameState = newGameState;
				newGameState = 0;
			}
			
			Display.update();
			Display.sync(120);
		}

		
		Display.destroy();
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
	
	/*
	 	
	

	 */
}