package com.abauer.oceans.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JComponent;

public class Game extends JComponent implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	float value[][] = new float[5][5];
	int square[][] = new int[128][2];
	
	public Game(){
		addMouseListener(this);
		initArray();
	}
	
	private void initArray(){
		//color range
//		0..0,0..150,255..255
//		for(int x = 0; x<150*10; x+=20){
//			g.setColor(new Color(0,x/10,255));
//			g.fillRect(x,10,10,10);
//		}
		
		//2048x2048 (+1)
	
		value[0][0]=value[4][0]=value[0][4]=value[4][4]=1.0f;
		
		square[0][0]=square[0][1]=0;
		square[1][0]=square[1][1]=4;
		for(int index=0; index<1; index++){
			System.out.println("Outer Loop"+(index+1));
			for(int count=0; count<(Math.pow(4, index)); count++){
				//square method
				System.out.println("Square Loop "+(1+count));
				int xavg= (short)((square[0][0]+square[1][0])/2);
				int yavg= (short)((square[0][1]+square[1][1])/2);
				value[xavg][yavg]=(average(value[0][0],value[0][1],value[4][0],value[0][4]))+random();
				System.out.println(value[xavg][yavg]);
			}
		
			for(int count=0; count<(1); count++){
				//diamondMeethod(value,coord);
			}			
		}
		
	}
	
	private float random(){
		return (float) ((Math.random()*2)-1);
	}
	
	private float average(float a, float b, float c, float d){
		return (float)((a+b+c+d)/4);
	}
	
	public void paint(Graphics g){		
		
		for(int index=0; index<5; index++){
			for(int count=0; count<5; count++){
				String temp = new DecimalFormat("0.00").format((double)value[index][count]);
				g.drawString(temp,(index+1)*50+200,(count+1)*50+200);
			}
		}
		
		g.setColor(Color.RED);
		g.fillRect(1895, 0, 25, 25); //exit box
	}
	
	private boolean doExit(int x, int y) {return x>1895 && y<25;} 
	public void mouseClicked(MouseEvent e) { if(doExit(e.getX(),e.getY())) System.exit(0);}
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	
}
