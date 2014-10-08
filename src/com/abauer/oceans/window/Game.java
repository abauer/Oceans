package com.abauer.oceans.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Game extends JComponent implements MouseListener{

	private static final long serialVersionUID = 1L;
	
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
//		float value[][] = new float[5][5];
//		
//		short coord[][] = new short[128][2];
//	
//		value[0][0]=value[4][0]=value[0][4]=value[4][4]=1.0f;
//		coord[0][0]=coord[0][1]=coord[1][0]=coord[2][1]=0;
//		coord[1][1]=coord[2][0]=coord[3][0]=coord[3][1]=4;
//		short points=4;
//		for(int index=0; index<5; index++){
//			for(int count=0; count<points; count+=2){
//				short xavg = (short) ((coord[count][0])/2);
//				short yavg = (short) ((ys[count]-ys[count+1])/2);
//			}
//		}
		
	}
	
	public void paint(Graphics g){		
		
		
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
