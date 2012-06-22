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


/*
 * Standards: right: positive X
 * 				up: positive Y
 * 				angles, measured clockwise from positive X
 * 
 */
public class Level
{
	//Needs a list of planets
	//Needs a sun
	//List of enemies
	
	Player player = new Player();
	
	Texture background;
	public Planet[] planets = new Planet[20];	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	
	public Sun theSun;
	public int numPlanets = 1;
	public int backgroundNum = 0;
	public float mass = 100.0f;
	public float planetInfluence = 0.0001f;
	
	int score = 0;
	
	//VariableChanger varWindow;
	
	public Level()
	{
		player.X = 400;
		player.Y = 300;
		player.width =24;
		player.height = 24;
		player.r = JAsteroidsUtil.distance(player.width, player.height);
		
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
		Enemy a = Enemy.newEnemy(0);
		enemies.add(a);
		
		//Create planets
		planets[0] = new Planet(700,-80);
		theSun = new Sun(0,0);				
	}

	public void update()
	{
		float mouseDX = (Mouse.getX()-400);
		float mouseDY = (Mouse.getY()-300);
		
		float dist = (float) Math.sqrt(mouseDX*mouseDX + mouseDY*mouseDY);
		
		player.goalRotationX = mouseDX/dist;
		player.goalRotationY = mouseDY/dist;
	
		
		if (!Mouse.isButtonDown(1))
		{
			player.rotX +=(player.goalRotationX-player.rotX)*0.05;
			player.rotY +=(player.goalRotationY-player.rotY)*0.05;
		}

		if (Mouse.isButtonDown(0) && player.gunRecharge>=5)
		{
			//FIRE!
			Bullet newB = new Bullet(player.gunType,player.X,player.Y,player.rotX,player.rotY);
			bullets.add(newB);
			player.gunRecharge = 0;	
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && player.boosterLeft > 0)
		{
			player.boosterLeft-=2;
			if (distance(player.speedX,player.speedY) <6.0f)
			{
				//Add to both, in balance
				player.speedX += player.rotX*0.08f;
				player.speedY -= player.rotY*0.08f;
			}
		}/*
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			player.Y +=1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			player.Y -=1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			player.X -=1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			player.X +=1;
		}*/
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) // Do collision checking
		{
			if (enemies.size()<40)
			{
				Enemy a = Enemy.newEnemy(0);
				a.X = (float) (Math.random()*400);
				a.Y = (float) (Math.random()*500);
				enemies.add(a);
			}
		}
		

		player.X+=player.speedX;
		player.Y+=player.speedY;
		if (player.X > 2000)
		{
			player.speedX = -player.speedX;
			player.X = 1999;
		}
		if (player.X<-2000)
		{
			player.speedX = -player.speedX;
			player.X = -1999;
		}
		
		if (player.Y > 2000)
		{
			player.speedY = -player.speedY;
			player.Y = 1999;
		}
		if (player.Y<-2000)
		{
			player.speedY = -player.speedY;
			player.Y = -1999;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) )
		{
			if (JAsteroidsUtil.distance(player.speedX,player.speedY) >2.5f)
			{
				if (player.speedX>0.03)
				{
					player.speedX-=0.05;
				}
				if (player.speedX<-0.03)
				{
					player.speedX+=0.05;
				}
				
				if (player.speedY>0.03)
				{
					player.speedY-=0.05;
				}
				if (player.speedY<-0.03)
				{
					player.speedY+=0.05;
				}
			}
			if (JAsteroidsUtil.distance(player.speedX,player.speedY) <2.5f)
			{
				if (player.speedX>0.01)
				{
					player.speedX-=0.03;
				}
				if (player.speedX<-0.01)
				{
					player.speedX+=0.03;
				}
				
				if (player.speedY>0.01)
				{
					player.speedY-=0.03;
				}
				if (player.speedY<-0.01)
				{
					player.speedY+=0.03;
				}
			}
		}
		
		
		
		//Do the influence of the planets
		
		for (int i = 0;i<1;i++)
		{
			float planetDist = JAsteroidsUtil.distance(player.X-planets[i].x,player.Y-planets[i].y);
			
			if (planetDist < 3000)
			{
				float F =planets[i].weight/planetDist;
				float angle = (float) Math.atan2(player.X-planets[i].x, player.Y-planets[i].y);
				
				player.speedX -= (float) (Math.sin(angle)*planetInfluence*F);
				player.speedY -= (float) (Math.cos(angle)*planetInfluence*F);
			}
		}
		
		//Influence of the sun
		float sunDist = distance(player.X-theSun.x,player.Y-theSun.y);
		
		if (sunDist < 1400 && sunDist>20)//A minimum distance. By now a collision should have happened
		{
			float F = theSun.weight/sunDist;
			float angle = (float) Math.atan2(player.X-theSun.x, player.Y-theSun.y);
			
			player.speedX -= (float) (Math.sin(angle)*planetInfluence*F);
			player.speedY -= (float) (Math.cos(angle)*planetInfluence*F);
		}
		
		//Update Bullets
		for (int i = 0 ; i < bullets.size();i++)
		{
			if (bullets.get(i).update() == 1)
			{
				bullets.remove(i);
			}
		}
		
		//Update Enemies
		for (int e = 0;e<enemies.size();e++)
		{
			enemies.get(e).update();
		}
		
		
		//Update planets
		for (int i = 0; i < 1; i++)
		{
			float angleIncrement = 0.10f/(float)planets[i].orbitDistance;
			planets[i].orbitAngle += angleIncrement;
			planets[i].x = (int) (Math.sin(planets[i].orbitAngle)*planets[i].orbitDistance);
			planets[i].y = (int) (Math.cos(planets[i].orbitAngle)*planets[i].orbitDistance);
		}
		
		if (player.boosterLeft < player.boosterMax)
		{
			player.boosterLeft++;
		}
		//Display.setTitle("BoosterLeft :"+BoosterLeft);
		
		if (player.gunRecharge<5)
		{
			player.gunRecharge++;
		}
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////      Collision Checking      //////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Player with enemies
		for ( int e =0;e<enemies.size();e++)
		{
			Enemy CurrEnemy = enemies.get(e);
			
			if (player.checkCollision(CurrEnemy) == true)
			{
				player.speedX = - player.speedX*0.4f;
				player.speedY = - player.speedY*0.4f;
			}
			
			for (int b = 0;b<bullets.size();b++)
			{
				if (CurrEnemy.checkCollision(bullets.get(b)) == true)
				{
					CurrEnemy.health-=10;
					bullets.remove(b);
					
					if (CurrEnemy.health <= 0)
					{
						//TODO:
						//Add action for when enemy is dead
						enemies.remove(e);
						score+=10;
					}
				}
			}
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
		//First, determine the top left corner
		int cornerX = (int) (player.X - 400);
		int cornerY = (int) (player.Y - 300);
		
		
		Display.setTitle("Score "+score+"\tEnemies Left "+enemies.size());
		
		/*
		 * First render the background. To do this, calculate how much of each tile is sticking out 
		 * on the left of the sceen. Then subtract another 255 to be sure we start drawing outside 
		 * the screen. The negative
		 * 0-------255---------------------------------------------------->
		 * |'-.                     
		 * |    '-.           
		 255        '-.______________ 
		 * |        |   '-.     Tile |
		 * |        |       '-.____________________________________
		 * |        |         | CornerX,Y                          |
		 * |        |_________|                                    |
		 * |             a    |                                    |
		 * |                  |						visible screen      |
		 * |                  |                                    |
		 * |
		 * |	a - distance one tile overlaps
		 * |
		 * Y
		 */
		int backgroundStartX= -(cornerX%255) -255;
		int backgroundStartY= -(cornerY%255) -255;
		
		
		for (int i = 0;i<6;i++)
		{
			for (int j = 0;j<5;j++)
			{	
				RenderBackground(backgroundNum,backgroundStartX+ i*255, backgroundStartY+ j*255);
			}
		}
		
		
		/*
		 * Draw the sun
		 */
		theSun.render(cornerX,cornerY);

		
		//Render Planets
		for (int i =0;i<numPlanets;i++)
		{
			planets[i].render(cornerX,cornerY);
		}
		
		//Render Bullets
		for (int i = 0;i<bullets.size();i++)
		{
			bullets.get(i).render(cornerX,cornerY);
		}
		
		
		//Render Enemies
		for (int e = 0; e<enemies.size(); e++)
		{
			if (enemies.get(e).health > 0)
			{
				enemies.get(e).render(cornerX,cornerY);
			}
		}
		
		//Render PLayer		
		TextureManager.playership.bind();
		//Draw a quad in the centre of the screen
		GL11.glPushMatrix();
		GL11.glTranslatef(400, 300, 0);
		GL11.glRotatef((float) Math.toDegrees(Math.atan2(player.rotY,player.rotX)), 0f, 0f, -1f);
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







