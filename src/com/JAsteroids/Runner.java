package com.JAsteroids;

import java.util.Arrays;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import com.JAsteroids.LevelStuff.*;
import com.JAsteroids.Render.*;

import java.lang.reflect.Field;

 
public class Runner
{
	int gameState = 0;	//0 is the first game state
	int newGameState = 0;
	
	Language stringLookup = new Language();
	Settings currentSettings;
	 
	public void start(String[] args)
	{		
		//TODO : Read Langauge from file
		//TODO : Check Command Line arguments
		
		currentSettings = new Settings();
		currentSettings.readFromFile("options.txt");
		
		try
		{
			//TODO make resolution read from file
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
		
		DrawerType[] drawers = new DrawerType[6];
	 
		drawers[0] = new DrawerMenu(currentSettings,stringLookup);
		drawers[5] = new DrawerLevel();
		
		
		
		while (!Display.isCloseRequested() && gameState != -1)
		{ 
			GL11.glClearColor(0.0f,0.0f,0.0f,1.0f);
			newGameState = drawers[gameState].render();
			
			/*
			 * How this works:
			 * 
			 * Every Frame, the drawer's render() method is called. This returns a value, indicating which
			 * state the game must move to next. To correspond with the array of drawers, values from 0 
			 * upwards indicate different states in the game. A -1 indicates a quit, and a -2 indicates no
			 * change.
			 * 
			 * 
			 *  0 - menu
			 *  1 - settings
			 *  2 - load Game 
			 *  3 -
			 *  4 - 
			 *  5 - NewLevel
			 *  
			 *  How are levelNames, etc going to be passed around?
			 */
			
			if (newGameState  >= 0)
			{
				System.out.println("Change from "+gameState+" to " + newGameState);
				/*
				 * Levels in use:
				 * 1 - Menu
				 * 5 - New Game
				 */
				//If There has been a change in gameState
				if ((newGameState != gameState) && (newGameState >0))
				{
					drawers[gameState].end();
					drawers[newGameState].begin();
				}

				gameState = newGameState;
			}
			
			if (newGameState == -1)
			{
				drawers[gameState].end();
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
	 
	public static void main(String[] args)
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
		displayExample.start(args);
	}
	 
	/*
	 	
	

	 */
}