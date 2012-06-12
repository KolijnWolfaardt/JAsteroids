package com.JAsteroids.LevelStuff;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.JAsteroids.*;
import com.JAsteroids.LevelStuff.*;

public class Level
{
	//Needs a list of planets
	//Needs a sun
	//List of enemies
	Texture background;
	public Planet[] planets = new Planet[20];
	Enemy[] enemies = new Enemy[20];
	
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public Sun theSun;
	
	
	//Regarding the player
	public int numPlanets = 1;
	public int numEnemies = 1;
	
	public float playerX;
	public float playerY;
	public float rotX = 1;
	public float rotY = 0;
	public float goalRotationX = 0.0f;
	public float goalRotationY = 0.0f;
	public float playerXSpeed;
	public float playerYSpeed;
	public int gunRecharge = 30;
	public int gunType = 0;
	
	public float BoosterLeft = 100;
	
	public int backgroundNum = 0;
	
	public float mass = 100.0f;
	
	public float planetInfluence = 0.0001f;
	

	
	//VariableChanger varWindow;
	
	public Level()
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
		
		//Create enemies
		enemies[0] = new Enemy();
		
		//Create planets
		planets[0] = new Planet(700,-80);
		theSun = new Sun(0,0);
		
		//varWindow = new VariableChanger();
		
	}

	public void update()
	{
		float mouseDX = 400-Mouse.getX();
		float mouseDY = 600-Mouse.getY()-300;
		
		float dist = (float) Math.sqrt(mouseDX*mouseDX + mouseDY*mouseDY);
		
		goalRotationX = -mouseDX/dist;
		goalRotationY = -mouseDY/dist;
	
		
		if (!Mouse.isButtonDown(1))
		{
			rotX +=(goalRotationX-rotX)*0.05;
		rotY +=(goalRotationY-rotY)*0.05;
		}
		/*
		 goalRotationX = (float) Math.atan2(400-Mouse.getX(), 600-Mouse.getY()-300);
		
		 
		 if ((rotation-goalRotationX)%(Math.PI*2)> 0.1f)
			 rotation-=0.08f;
		 else if ((rotation-goalRotationX)%(Math.PI*2) < -0.1f)
			 rotation +=0.08f;*/
		 
		 //Display.setTitle("ROT :" +rotation+"\tGROT :"+goalRotationX +"\tAdd :"+(rotation-goalRotationX)%(2*Math.PI));
		 
		 //rotation = goalRotation;
		
		if (Mouse.isButtonDown(0) && gunRecharge>=10)
		{
			//FIRE!
			Bullet newB = new Bullet(gunType,playerX,playerY,rotX,rotY);
			bullets.add(newB);
			gunRecharge = 0;	
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && BoosterLeft > 0)
		{
			BoosterLeft--;
			if (distance(playerXSpeed,playerYSpeed) <6.0f)
			{
				//Add to both, in balance
				playerXSpeed += rotX*0.08f;
				playerYSpeed -= rotY*0.08f;
			}
		}
		

		playerX+=playerXSpeed;
		playerY+=playerYSpeed;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) )
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
			
			if (planetDist < 3000)
			{
				float F =planets[i].weight/planetDist;
				float angle = (float) Math.atan2(playerX-planets[i].x, playerY-planets[i].y);
				
				playerXSpeed -= (float) (Math.sin(angle)*planetInfluence*F);
				playerYSpeed -= (float) (Math.cos(angle)*planetInfluence*F);
			}
		}
		
		//Influence of the sun
		float sunDist = distance(playerX-theSun.x,playerY-theSun.y);
		
		if (sunDist < 1400 && sunDist>20)//A minimum distance. By now a collision should have happened
		{
			float F = theSun.weight/sunDist;
			float angle = (float) Math.atan2(playerX-theSun.x, playerY-theSun.y);
			
			playerXSpeed -= (float) (Math.sin(angle)*planetInfluence*F);
			playerYSpeed -= (float) (Math.cos(angle)*planetInfluence*F);
		}
		
		//Update Bullets
		for (int i = 0 ; i < bullets.size();i++)
		{
			if (bullets.get(i).update() == 1)
			{
				bullets.remove(i);
			}
		}
		
		
		
		//Update planets
		for (int i = 0; i < 1; i++)
		{
			float angleIncrement = 0.10f/(float)planets[i].orbitDistance;
			planets[i].orbitAngle += angleIncrement;
			planets[i].x = (int) (Math.sin(planets[i].orbitAngle)*planets[i].orbitDistance);
			planets[i].y = (int) (Math.cos(planets[i].orbitAngle)*planets[i].orbitDistance);
		}
		
		if (BoosterLeft < 100)
		{
			BoosterLeft+=5;
		}
		//Display.setTitle("BoosterLeft :"+BoosterLeft);
		
		if (gunRecharge<10)
		{
			gunRecharge++;
		}
	}
	
	public static float distance(float a, float b)
	{
		return (float) Math.sqrt(a*a+b*b);
	}
	
	/**
	 * Renders the current Level
	 * 
	 * This method assumes LWJGL has been initialized
	 */
	public void render()
	{
		int xMax = (int) (playerX+600);
		int xMin = (int) (playerY-600);
		
		int yMax = (int) (playerY+400);
		int yMin = (int) (playerY-400);
		
		int offsetX = (int) (400-playerX);
		int offsetY = (int) (300-playerY);
		Display.setTitle("OffX"+offsetX+"\tOffy"+offsetY);
		
		
		//Render Background
		int pos1= 255- (int) (playerX%255);
		int pos2= 255- (int) (playerY%255);
		
		for (int i = -3;i<3;i++)
		{
			for (int j = -3;j<3;j++)
			{	
				RenderBackground(backgroundNum,pos1+i*255,pos2+j*255);
			}
		}
		
		//Render Sun
		theSun.render(offsetX,offsetY);

		
		//Render Planets
		for (int i =0;i<numPlanets;i++)
		{
			planets[i].render(offsetX,offsetY);
		}
		
		//Render Bullets
		for (int i = 0;i<bullets.size();i++)
		{
			bullets.get(i).render(offsetX,offsetY);
		}
		
		
		//Render Enemies
		for (int i = 0; i<numEnemies; i++)
		{
			enemies[i].render(offsetX,offsetY);
		}
		
		//Render PLayer		
		TextureManager.playership.bind();
		//Draw a quad in the centre of the screen
		GL11.glPushMatrix();
		GL11.glTranslatef(400, 300, 0);
		GL11.glRotatef((float) Math.toDegrees(Math.atan2(rotX,rotY)), 0f, 0f, 1f);
		GL11.glTranslatef(-400, -300, 0);
		
			GL11.glBegin(GL11.GL_QUADS);
				
				GL11.glTexCoord2f(0.0f,0.0f);
				GL11.glVertex2f(376, 276);
				
				GL11.glTexCoord2f(TextureManager.playership.getWidth(),0.0f);
				GL11.glVertex2f(424, 276);
				
				GL11.glTexCoord2f(TextureManager.playership.getWidth(),TextureManager.playership.getHeight());
				GL11.glVertex2f(424, 324);
				
				GL11.glTexCoord2f(0.0f,TextureManager.playership.getHeight());
				GL11.glVertex2f(376, 324);	
				
			GL11.glEnd();	
		GL11.glPopMatrix();
	}
	
	public static void RenderBackground(int num, int xpos, int ypos)
	{
		TextureManager.backgrounds[num].bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex2f(xpos, ypos);
		
		GL11.glTexCoord2f(TextureManager.backgrounds[num].getWidth(),0.0f);
		GL11.glVertex2f(xpos+255, ypos);
		
		GL11.glTexCoord2f(TextureManager.backgrounds[num].getWidth(),TextureManager.backgrounds[num].getHeight());
		GL11.glVertex2f(xpos+255, ypos+255);
		
		GL11.glTexCoord2f(0.0f,TextureManager.backgrounds[num].getHeight());
		GL11.glVertex2f(xpos, ypos+255);	
	
	GL11.glEnd();
	}
	
}







