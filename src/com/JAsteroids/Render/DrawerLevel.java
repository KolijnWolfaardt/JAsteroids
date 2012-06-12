package com.JAsteroids.Render;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.*;
import com.JAsteroids.LevelStuff.*;

public class DrawerLevel extends DrawerType
{
	Level currentLevel;
	
	public DrawerLevel()
	{
		//Create a new player profile
		//Create a new cell
		currentLevel= new Level();
		TextureManager.loadTextures();
	}

	
	public int render()
	{		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		currentLevel.render();
		currentLevel.update();
		return -2;	//-2 means no change
	}
	
	public void end()
	{
		TextureManager.freeTextures();
	}
}
