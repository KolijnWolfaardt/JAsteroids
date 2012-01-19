package com.JAsteroids;

import org.lwjgl.opengl.GL11;

public class LevelClass
{
	Cell currentCell;
	
	public LevelClass ()
	{
		//Create a new player profile
		//Create a new cell
		currentCell= new Cell();
		TextureManager.loadTextures();
	}

	
	public int render()
	{
		int returnValue = 0; //0 means no change
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		RenderEngine.Render(currentCell);
		currentCell.update();
		return returnValue;
	}
}
