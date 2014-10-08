package com.abauer.oceans.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JComponent;

public class Game extends JComponent implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	float value[][] = new float[9][9];
	
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
	
		value[0][0]=value[8][0]=value[0][8]=value[8][8]=1.0f;
		diamondMethod(new int[]{0,0,8,8},new int[]{0,8,0,8},1.0,3);
	}
	
	private void squareMethod(int x, int y,int distance,double rand, int iterations){
		iterations-=1;
		value[x-distance][y]=average(value[x][y],value[x-distance][y-distance],value[x-distance][y+distance])+random(1.0);
		value[x+distance][y]=average(value[x][y],value[x+distance][y-distance],value[x+distance][y+distance])+random(1.0);
		value[x][y-distance]=average(value[x][y],value[x-distance][y-distance],value[x+distance][y-distance])+random(1.0);
		value[x][y+distance]=average(value[x][y],value[x-distance][y+distance],value[x+distance][y+distance])+random(1.0);
		if(iterations>0){
			diamondMethod(new int[]{x-distance,x-distance,x,x},new int[]{y-distance,y,y-distance,y},rand/2,iterations);
			diamondMethod(new int[]{x-distance,x-distance,x,x},new int[]{y,y+distance,y,y+distance},rand/2,iterations);
			diamondMethod(new int[]{x,x,x+distance,x+distance},new int[]{y-distance,y,y-distance,y},rand/2,iterations);
			diamondMethod(new int[]{x,x,x+distance,x+distance},new int[]{y,y+distance,y,y+distance},rand/2,iterations);
		}
	}
	
	private void diamondMethod(int[] x, int[] y,double rand,int iterations){
		int x1=(x[3]+x[0])/2;
		int y1=(y[3]+y[0])/2;
		value[x1][y1]=average(value[x[0]][y[0]],value[x[3]][y[0]],value[x[0]][y[3]],value[x[3]][y[3]])+random(1.0);
		squareMethod(x1,y1,x1-x[0],rand/2,iterations);
	}
	
	private float random(double range){
		return (float) ((Math.random()*2*range)-1*range);
	}
	
	private float average(float... a){
		float sum = 0f;
		for(int index=0; index<a.length; index++){
			sum+=a[index];
		}
		return (float)(sum/a.length);
	}
	
	public void paint(Graphics g){		
		
		for(int index=0; index<9; index++){
			for(int count=0; count<9; count++){
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
