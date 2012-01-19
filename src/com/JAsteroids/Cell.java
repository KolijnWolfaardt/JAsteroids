package com.JAsteroids;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.JAsteroids.LevelStuff.*;

public class Cell
{
	//Needs a list of planets
	//Needs a sun
	//List of enemies
	Texture background;
	Planet[] planets = new Planet[20];
	Sun theSun;
	
	
	//Regarding the player
	public int numPlanets = 1;
	public float playerX;
	public float playerY;
	public float rotation = 0.0f;
	public float goalRotation = 0.0f;
	public float playerXSpeed;
	public float playerYSpeed;
	
	public float mass = 100.0f;
	
	public Cell()
	{
		playerX = 400;
		playerY = 0;
		
		try
		{
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/menu/newGame.png"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create planets
		planets[0] = new Planet(700,-80);
		theSun = new Sun(0,0);
		
	}

	public void update()
	{
		 goalRotation = (float) Math.atan2(400-Mouse.getX(), 600-Mouse.getY()-300);
		 rotation = goalRotation;
		if (Mouse.isButtonDown(0))
		{
			if (distance(playerXSpeed,playerYSpeed) <6.0f)
			{
				//Add to both, in balance
				playerXSpeed -= Math.sin(rotation)*0.12f;
				playerYSpeed += Math.cos(rotation)*0.12f;
			}
		}
		
		if (rotation > Math.PI*2)
		{
			rotation -= Math.PI*2;
		}
		if (rotation < 0)
		{
			rotation += Math.PI*2;
		}
		playerX+=playerXSpeed;
		playerY+=playerYSpeed;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			if (distance(playerXSpeed,playerYSpeed) >2.5f)
			{
				if (playerXSpeed>0.03)
				{
					playerXSpeed-=0.05;
				}
				if (playerXSpeed<-0.03)
				{
					playerXSpeed+=0.05;
				}
				
				if (playerYSpeed>0.03)
				{
					playerYSpeed-=0.05;
				}
				if (playerYSpeed<-0.03)
				{
					playerYSpeed+=0.05;
				}
			}
			if (distance(playerXSpeed,playerYSpeed) <2.5f)
			{
				if (playerXSpeed>0.01)
				{
					playerXSpeed-=0.03;
				}
				if (playerXSpeed<-0.01)
				{
					playerXSpeed+=0.03;
				}
				
				if (playerYSpeed>0.01)
				{
					playerYSpeed-=0.03;
				}
				if (playerYSpeed<-0.01)
				{
					playerYSpeed+=0.03;
				}
			}
		}
		
		//Do the influence of the planets
		
		for (int i = 0;i<1;i++)
		{
			float planetDist = distance(playerX-planets[i].x,playerY-planets[i].y);
			
			if (planetDist < 1000)
			{
				float F =planets[i].weight/planetDist;
				float angle = (float) Math.atan2(playerX-planets[i].x, playerY-planets[i].y);
				
				playerXSpeed -= (float) (Math.sin(angle)*0.0003*F);
				playerYSpeed -= (float) (Math.cos(angle)*0.0003*F);
			}
		}
		
		//Influence of the sun
		float planetDist = distance(playerX-theSun.x,playerY-theSun.y);
		
		if (planetDist < 1000)
		{
			float F = theSun.weight/planetDist;
			float angle = (float) Math.atan2(playerX-theSun.x, playerY-theSun.y);
			
			playerXSpeed -= (float) (Math.sin(angle)*0.0003*F);
			playerYSpeed -= (float) (Math.cos(angle)*0.0003*F);
		}
		
		Display.setTitle("X :" +playerX+"\tY :"+playerY);
		
		
		
		//Update planets
		for (int i = 0; i < 1; i++)
		{
			float angleIncrement = 0.10f/(float)planets[i].orbitDistance;
			planets[i].orbitAngle += angleIncrement;
			planets[i].x = (int) (Math.sin(planets[i].orbitAngle)*planets[i].orbitDistance);
			planets[i].y = (int) (Math.cos(planets[i].orbitAngle)*planets[i].orbitDistance);
		}
	}
	
	public float distance(float a, float b)
	{
		return (float) Math.sqrt(a*a+b*b);
	}
	
}







