package com.JAsteroids.Render;

import com.JAsteroids.*;

public class DrawerType
{
	
	/**
	 * Implementations:
	 * 	DrawerLevel
	 * 	DrawerMenu
	 */
	
	public DrawerType ()
	{

	}
	
	public DrawerType(Settings newSet, Language newLang)
	{
	
	}
	
	
	/**
	 * Things that happen when this Drawer is created
	 * 
	 * A class that implements the Drawer type should override this function with
	 * it's own. Typical actions in the function is loading textures.
	 */
	public void begin()
	{
		
	}

	/**
	 * This function is called every time the screen is updated.
	 * 
	 * Implementations of this class should render in this function
	 * 
	 * @return Returns an boolean to indicate is this  DrawerType is done. i.e. if the level has finished.
	 */
	public int render()
	{
		return -2;
	}
	
	
	/**
	 * Things that must happen when this Drawer is destroyed
	 * 
	 * A class that implements the Drawer type should override this function with
	 * it's own. Typical actions in the function is freeing textures.
	 */
	public void end()
	{
	}
}
