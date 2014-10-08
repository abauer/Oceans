package com.abauer.oceans.window;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	Game g;
	
	WindowSize ws = WindowSize.W1920_H1080;
	//TODO file reading options
	//TODO auto-detection
	//TODO add more sizes
	
	public GameWindow(){
		initGame();
		initWindow();
	}
	
	public void showMenu(){
		setVisible(true);
		toFront();
	}
	
	private void initGame(){
		g = new Game();
	}
	
	private void initWindow(){
		dispose();
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(ws);
		setLocation(ws);
		add(g);
	}
	
	private void setSize(WindowSize ws)
	{
		if (ws == WindowSize.W1920_H1080)
			setSize(1920,1080);
	}
	
	private void setLocation(WindowSize ws){
		if (ws == WindowSize.W1920_H1080)
			setLocation(0,0);
	}	
}
