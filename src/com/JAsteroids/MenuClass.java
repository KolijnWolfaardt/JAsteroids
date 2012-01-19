package com.JAsteroids;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.JAsteroids.Gui.Button;

public class MenuClass
{	
	
	Texture newGame;
	Texture exit;
	Texture title;
	Texture backgroundPlanet;
	
	Settings mySettings;
	Language myLang;
	
	Button newGameButton;
	Button exitGameButton;
	
	public MenuClass(Settings set, Language lang)
	{
		//Load all required textures from file here
		
		mySettings = set;
		myLang = lang;
		
		newGameButton = new Button(10, 250, 150, 75);
		exitGameButton = new Button(10,350,150,75);
		
		try
		{
			Button.myTexture =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/menu/newGame.png"));
			Button.textureSet();
			//exit =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/menu/exit.png"));
			title =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/menu/title.png"));
			backgroundPlanet =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/menu/planet1.png"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Load all settings
	}
	
	public void endClass()
	{
		Button.myTexture.release();
		//exit.release();
		title.release();
		backgroundPlanet.release();
	}

	public int render()
	{
		int returnValue = 1;
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		 		
		GL11.glColor4f(1.0f,1.0f,1.0f,1.0f);
		backgroundPlanet.bind();
		GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(250, 150);
			
			GL11.glTexCoord2f(backgroundPlanet.getWidth(),0.0f);
			GL11.glVertex2f(550, 150);
			
			GL11.glTexCoord2f(backgroundPlanet.getWidth(),backgroundPlanet.getHeight());
			GL11.glVertex2f(550, 450);
			
			GL11.glTexCoord2f(0.0f,backgroundPlanet.getHeight());
			GL11.glVertex2f(250, 450);	
			
		GL11.glEnd();	
		
		title.bind(); //700x200
		GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f((mySettings.windowX-700)/2, 0);
			
			GL11.glTexCoord2f(title.getWidth(),0.0f);
			GL11.glVertex2f((mySettings.windowX+700)/2, 0);
			
			GL11.glTexCoord2f(title.getWidth(),title.getHeight());
			GL11.glVertex2f((mySettings.windowX+700)/2, 200);
			
			GL11.glTexCoord2f(0.0f,title.getHeight());
			GL11.glVertex2f((mySettings.windowX-700)/2, 200);	
			
		GL11.glEnd();
		
		newGameButton.render();
		exitGameButton.render();
		
		int Mx = Mouse.getX();
		int My = mySettings.windowY-Mouse.getY();

		if (newGameButton.update(Mx,My))
		{
			returnValue = 5;
		}
		if (exitGameButton.update(Mx,My))
		{
			returnValue = 0;
		}

		return returnValue;
	}
	
}
