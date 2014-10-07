package com.abauer.oceans;

import com.abauer.oceans.window.GameWindow;


public class Oceans {

	static GameWindow gw;
	
	public static void main(String args[]){
		initGame();
		startGame();
	}
	
	public static void initGame(){
		gw = new GameWindow();
	}
	
	public static void startGame(){
		gw.showMenu();
	}
	
}

