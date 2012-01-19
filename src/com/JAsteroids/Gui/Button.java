package com.JAsteroids.Gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Button
{
	int x;
	int y;
	int w;
	int h;
	
	int hoverPos = 0;
	//0 - normal state
	//1 - mouse over
	//2 - mouse clicked
	
	public static Texture myTexture;
	static boolean textureSet = false;
	
	boolean previousmouse = false;
	
	static float textureYDivs;
	
	public Button(int xPos, int yPos, int width, int height)
	{
		x = xPos;
		y = yPos;
		h = height;
		w = width;
		textureYDivs= 0.0f;
	}
	
	/*
	 * Call this method to indicate a texture has been set
	 */
	public static void textureSet()
	{
		Button.textureSet=true;
		textureYDivs = Button.myTexture.getHeight()/3;
	}
	
	public boolean update(int Mx, int My)
	{
		boolean downNow = Mouse.isButtonDown(0);
		boolean returnValue = false;
		
		if ((Mx < x+w) && (Mx > x) && (My < y+h) && (My > y))
		{
			
			if (downNow)
			{
				hoverPos=2;
			}
			else
			{
				hoverPos = 1;
			}
			
			if (previousmouse == true && downNow == false) //Went from up to down
			{
				returnValue= true;
			}
			else
			{
				previousmouse = downNow;
				returnValue = false;
			}
		}
		else
		{
			hoverPos = 0;
			returnValue = false;
		}
		previousmouse= downNow;
		return returnValue;

	}
	
	public void render()
	{		
		if (textureSet)
		{
			Button.myTexture.bind();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0.0f,textureYDivs*hoverPos);
				GL11.glVertex2f(x, y);
				
				GL11.glTexCoord2f(myTexture.getWidth(),textureYDivs*hoverPos);
				GL11.glVertex2f(x+w, y);
				
				GL11.glTexCoord2f(myTexture.getWidth(),textureYDivs*(hoverPos+1));
				GL11.glVertex2f(x+w, y+h);
				
				GL11.glTexCoord2f(0.0f,textureYDivs*(hoverPos+1));
				GL11.glVertex2f(x, y+h);	
			GL11.glEnd();
		}
	}
}
