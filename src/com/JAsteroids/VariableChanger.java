package com.JAsteroids;

import java.awt.BorderLayout;

import javax.swing.*;


public class VariableChanger
{
	JFrame varFrame;
	JScrollBar[] var1;
	
	public VariableChanger()
	{
		varFrame = new JFrame("Variables");
		varFrame.setSize(200,600);
		
		
		var1 = new JScrollBar[10];
		for (int i = 0; i<10;i++)
		{
			var1[i] = new JScrollBar(0,50,10,0,100);
			varFrame.getContentPane().add(var1[i], BorderLayout.CENTER);
		}
		varFrame.setVisible(true);
	}
	
	public void addVariable(float maxvalue,float currentvalue, float minvalue)
	{
		
	}
}
