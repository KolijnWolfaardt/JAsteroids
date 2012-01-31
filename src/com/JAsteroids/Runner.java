package com.JAsteroids;

import java.util.Arrays;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import java.lang.reflect.Field;

 
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
	
	
	public static void addLibraryPath(String pathToAdd) throws Exception
	{
		final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
		usrPathsField.setAccessible(true);

		//get array of paths
		final String[] paths = (String[])usrPathsField.get(null);

		//check if the path to add is already present
		for(String path : paths)
		{
			if(path.equals(pathToAdd))
			{
				return;
			}
		}

		//add the new path
		final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
		newPaths[newPaths.length-1] = pathToAdd;
		usrPathsField.set(null, newPaths);
	}
	 
	public static void main(String[] argv)
	{	
		//System.setProperty("java.class.path",System.getProperty("java.class.path")+";"+System.getProperty("java.class.path")+"\\jar\\lwjgl.jar");
		//System.setProperty("java.class.path",System.getProperty("java.class.path")+";"+System.getProperty("user.dir")+"\\jar\\lwjgl.jar;"+System.getProperty("user.dir")+"\\jar\\slick.jar");
		//System.out.println(System.getProperty("java.class.path"));		
		//System.out.println(System.getProperty("user.dir"));
		try
		{
			Runner.addLibraryPath("./native/windows");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.println("Failed to fin lwjgl natives. Run with -Djava.library.path=(Path to natives) in the command line");
			e.printStackTrace();
		}
		Runner displayExample = new Runner();
		displayExample.start();
	}
	 
	/*
	 	
	

	 */
}