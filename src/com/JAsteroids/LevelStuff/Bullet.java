package com.JAsteroids.LevelStuff;

import org.lwjgl.opengl.GL11;

import com.JAsteroids.TextureManager;
import com.JAsteroids.Vector2f;

public class Bullet extends MoveableObject
{
	public int bulletTexNum = 0;
	
	public float baseSpeed = 8.0f;
	
	public boolean gravity = false; //Affected by gravity
	public float distance = 0;
	
	public Bullet()
	{
		
	}
	
	public Bullet(int bulletType,float x, float y, float rotx, float roty)
	{
		collisionPoints = new Vector2f[4];
		collisionPoints[0] = new Vector2f(-19,-22);
		collisionPoints[1] = new Vector2f(18,-22);
		collisionPoints[2] = new Vector2f(18,20);
		collisionPoints[3] = new Vector2f(-19,20);
		
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
		GL11.glRotatef((float) Math.toDegrees(Math.atan2(rotY,rotX)), 0f, 0f, -1f);
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
		    
		GL11.glPopMatrix();
		

	}
}
