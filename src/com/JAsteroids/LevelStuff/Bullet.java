package com.JAsteroids.LevelStuff;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.TextureManager;

public class Bullet extends MoveableObject
{
	public int bulletTexNum = 0;
	
	public float X;
	public float Y;
	public float rotX = 1.0f;
	public float rotY = 0.0f;
	public float speedX = 0;
	public float speedY = 0;
	
	int boundingW = 30;
	int boundingH = 10;
	
	public float baseSpeed = 3.0f;
	
	public boolean gravity = false; //Affected by gravity
	public float distance = 0;
	
	public Bullet()
	{
	}
	
	public Bullet(int bulletType,float x, float y, float rotx, float roty)
	{
		bulletTexNum=bulletType;
		
		X = x;
		Y = y;
		
		rotX = rotx;
		rotY = roty;
		
		speedX = baseSpeed*rotx;
		speedY = baseSpeed*roty;
		
		width = 16;
		height = 16;
	}
	
	public int update ()
	{
		X+=speedX;
		Y-=speedY;
		distance+= Math.sqrt((speedX*speedX)+ (speedY*speedY));
		if (distance > 3500)
			return 1;
		else
			return 0;
	}
	
	public void render(int xpos, int ypos)
	{
		TextureManager.bullets[bulletTexNum].bind();


		GL11.glPushMatrix();
		GL11.glTranslatef((X-xpos), (Y-ypos), 0);
		GL11.glRotatef((float) Math.toDegrees(Math.atan2(rotX,rotY)), 0f, 0f, 1f);
		GL11.glTranslatef(-(X-xpos), -(Y-ypos), 0);
		
			
		
			GL11.glBegin(GL11.GL_QUADS);
				
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f((X-xpos)-width, (Y-ypos)-height);
			
			GL11.glTexCoord2f(TextureManager.bullets[bulletTexNum].getWidth(),0.0f);
			GL11.glVertex2f((X-xpos)+width, (Y-ypos)-height);
			
			GL11.glTexCoord2f(TextureManager.bullets[bulletTexNum].getWidth(),TextureManager.bullets[bulletTexNum].getHeight());
			GL11.glVertex2f((X-xpos)+width, (Y-ypos)+height);
			
			GL11.glTexCoord2f(0.0f,TextureManager.bullets[bulletTexNum].getHeight());
			GL11.glVertex2f((X-xpos)-width, (Y-ypos)+height);
				
			GL11.glEnd();	
		
			//Draw the bounding box if enabled
			GL11.glColor3f(1.0f, 0.5f, 0.5f);
			
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(100.0f, 100.0f);
			GL11.glVertex2f(100.0f, 10.0f);
			GL11.glVertex2f(125.0f, 50.0f);
			GL11.glVertex2f(125.0f, 10.0f);
			GL11.glEnd( );
			
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
		    
		GL11.glPopMatrix();
		

	}
}
