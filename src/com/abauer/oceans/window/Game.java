package com.abauer.oceans.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Game extends JComponent implements MouseListener{

	private static final long serialVersionUID = 1L;
	final static short size = 256;//perfect square
	float value[][] = new float[size+1][size+1];
	Image background;
	final static int pixelSize = 10;
	
	public Game(){
		background = new BufferedImage(size*pixelSize,size*pixelSize,BufferedImage.TYPE_INT_RGB);
		addMouseListener(this);
		setDoubleBuffered(true);
		initArray();
	}
	
	private void initArray(){
		value[0][0]=value[size][0]=value[0][size]=value[size][size]=1.0f;
//		diamondMethod(new int[]{0,0,size,size},new int[]{0,size,0,size},1.0, (int) (Math.log(size)/Math.log(2)) );
		iteritiveMethod();
		createImage();
	}
	
	private void createImage(){
Graphics g = background.getGraphics();
		
		for(int index=0; index<size+1; index++){
			for(int count=0; count<size+1; count++){
				float mul = (((value[index][count]+5)/12));
				g.setColor(new Color(1,(int)(180*mul),(int)((214*mul)+40)));
//				g.setColor(new Color((int)(254*mul),(int)(254*mul),(int)(254*mul)));
				g.fillRect((index*pixelSize),(count*pixelSize),pixelSize,pixelSize);
			}
		}
	}
	
	private void iteritiveMethod(){
		int dis = size;
		float rand =1.0f;
		while(dis>1){
			int newdis=dis/2;
			for(int index=0; index<size/dis; index++)
				for(int count=0; count<size/dis; count++){
					int x = index*dis+newdis; int y = count*dis+newdis;
					value[x][y]=average(value[x+newdis][y+newdis],value[x+newdis][y-newdis],value[x-newdis][y+newdis],value[x-newdis][y-newdis])+random(rand);
				}
			for(int index=0; index<(size/dis)*2+1; index++){
				if(index%2==0)
					for(int count=0; count<(size/dis)+0; count++){
						int x = index*newdis; int y = (count*2+1)*newdis;
						diamond(x,y,newdis,rand);
					}
				else
					for(int count=0; count<(size/dis)+1; count++){
						int x = index*newdis; int y = (count*2)*newdis;
						diamond(x,y,newdis,rand);
					}
			}
			dis/=2;
//			rand/=2;
		}
	}
	
	private void diamond(int x, int y, int dis,float rand){
		float avg=0f;
		if(x-dis<0)
			avg+=value[x-dis+size][y];
		else
			avg+=value[x-dis][y];
		if(y-dis<0)
			avg+=value[x][y-dis+size];
		else
			avg+=value[x][y-dis];
		if(x+dis>size)
			avg+=value[x+dis-size][y];
		else
			avg+=value[x+dis][y];
		if(y+dis>size)
			avg+=value[x][y+dis-size];
		else
			avg+=value[x][y+dis];
//		
//		int count = 0;
//		if(x-dis>0){
//			avg+=value[x-dis][y];
//			count++;
//		}
//		if(y-dis>0){
//			avg+=value[x][y-dis];
//			count++;
//		}			
//		if(x+dis<size){
//			avg+=value[x+dis][y];
//			count++;
//		}
//		if(y+dis<size){
//			avg+=value[x][y+dis];
//			count++;
//		}
//		if(x==size-1){
//			value[x][y]=value[0][y];
//			return;
//		}
//		if(y==size-1){
//			value[x][y]=value[x][0];
//			return;
//		}
		value[x][y]=(avg/4)+random(rand);
	}
	
	private void squareMethod(int x, int y,int distance,double rand, int iterations){
		iterations-=1;
		if(x-2*distance<0)
			value[x-distance][y]=average(value[x][y],value[x-distance][y-distance],value[x-distance][y+distance])+random(1.0);
		else
			value[x-distance][y]=average(value[x][y],value[x-distance][y-distance],value[x-distance][y+distance],value[x-2*distance][y])+random(1.0);
		if(x+2*distance>size-1)
			value[x+distance][y]=average(value[x][y],value[x+distance][y-distance],value[x+distance][y+distance])+random(1.0);
		else
			value[x+distance][y]=average(value[x][y],value[x+distance][y-distance],value[x+distance][y+distance],value[x+2*distance][y])+random(1.0);
		if(y-2*distance<0)
			value[x][y-distance]=average(value[x][y],value[x-distance][y-distance],value[x+distance][y-distance])+random(1.0);
		else
			value[x][y-distance]=average(value[x][y],value[x-distance][y-distance],value[x+distance][y-distance],value[x][y-2*distance])+random(1.0);
		if(y+2*distance>size-1)
			value[x][y+distance]=average(value[x][y],value[x-distance][y+distance],value[x+distance][y+distance])+random(1.0);
		else
			value[x][y+distance]=average(value[x][y],value[x-distance][y+distance],value[x+distance][y+distance],value[x][y+2*distance])+random(1.0);
		
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
		squareMethod(x1,y1,x1-x[0],rand/3,iterations);
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
	
	int sx=0;
	int sy=0;
	public void paint(Graphics g){
		g.drawImage(background, sx, sy, null);
		g.drawImage(background, sx, size*pixelSize+sy, null);
		g.drawImage(background, size*pixelSize+sx, sy, null);
		g.drawImage(background, size*pixelSize+sx, size*pixelSize+sy, null);
		g.setColor(Color.RED);
		g.fillRect(1895, 0, 25, 25); //exit box
		sx--;
		sy--;
		if(sx<=-size*pixelSize)
			sx=0;
		if(sy<=-size*pixelSize)
			sy=0;
//		initArray();
		repaint();
	}
	
	private boolean doExit(int x, int y) {return x>1895 && y<25;} 
	public void mouseClicked(MouseEvent e) { if(doExit(e.getX(),e.getY())) System.exit(0);}
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	
}
