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
	
	public static Texture[] backgrounds = new Texture[1];
	
	public static Texture[] enemies = new Texture[1];
	public static Texture[] bullets = new Texture[1];
	
	public static void loadTextures()
	{
		try
		{
			TextureManager.playership = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/ship.png"));
			TextureManager.planets[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/planet1.png")); 
			
			TextureManager.suns[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/Sun.png"));
			
			TextureManager.backgrounds[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/game/Stars1.png"));
			
			TextureManager.enemies[0] = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("images/game/enemies/enemy1.png"));
			
			TextureManager.bullets[0] = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("images/game/bullets/bullet0.png"));
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void freeTextures()
	{
		TextureManager.playership.release();
		TextureManager.planets[0].release();
		TextureManager.suns[0].release();
		TextureManager.backgrounds[0].release();
		TextureManager.enemies[0].release();
	}
}
