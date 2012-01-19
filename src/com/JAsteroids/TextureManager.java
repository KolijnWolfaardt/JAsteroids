package com.JAsteroids;

import java.io.IOException;

import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager
{
	public static Texture playership;
	public static Texture[] planets = new Texture[1];
	public static Texture[] suns = new Texture[1];
	
	public static void loadTextures()
	{
		try
		{
			TextureManager.playership = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/ship.png"));
			TextureManager.planets[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/planet1.png")); 
			
			TextureManager.suns[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/Sun.png"));
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
