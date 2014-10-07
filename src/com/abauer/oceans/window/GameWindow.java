package com.abauer.oceans.window;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	WindowSize ws = WindowSize.W1920_H1080;
	
	public GameWindow(){
		hideBorder();
	}
	
	public void showMenu(){
		setVisible(true);
		toFront();
	}
	
	private void hideBorder(){
		dispose();
		setUndecorated(true);
		setSize(ws);
		setAlwaysOnTop(true);
		setLocation(ws);
	}
	
	private void setSize(WindowSize ws)
	{
		if (ws == WindowSize.W1920_H1080)
			setSize(1920,1080);
	}
	
	private void setLocation(WindowSize ws){
		if (ws == WindowSize.W1920_H1080)
			setLocation(10,10);
	}
	
}
