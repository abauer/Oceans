package com.abauer.oceans.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JComponent;

public class Game extends JComponent implements MouseListener{

	private static final long serialVersionUID = 1L;
	short size = 16;//perfect square
	float value[][] = new float[size+1][size+1];
	
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
	
		value[0][0]=value[size][0]=value[0][size]=value[size][size]=1.0f;
//		diamondMethod(new int[]{0,0,size,size},new int[]{0,size,0,size},1.0, (int) (Math.log(size)/Math.log(2)) );
		iteritiveMethod();
	}
	
	private void iteritiveMethod(){
		
//		While the length of the side of the squares 
//		 is greater than zero {
//		 Pass through the array and perform the diamond 
//		 step for each square present.
//		 Pass through the array and perform the square 
//		 step for each diamond present.
//		 Reduce the random number range.
//		 }
		int dis = size;
		int iter = 0;
		while(iter<3){//dis>1
			int newdis=dis/2;
			for(int index=0; index<size/dis; index++){
				for(int count=0; count<size/dis; count++){
					System.out.println("diamond");
					int x = index*dis+newdis; int y = count*dis+newdis;
					value[x][y]=average(value[x+newdis][y+newdis],value[x+newdis][y-newdis],value[x-newdis][y+newdis],value[x-newdis][y-newdis])+random(1.0f);
				}
			}
			
			for(int index=0; index<(size/dis)*2+1; index++){
				if(index%2==0)
					for(int count=0; count<(size/dis)+0; count++){
						System.out.println("square even");
						int x = index*newdis; int y = (count*2+1)*newdis;
//						value[x][y]=average(value[x+newdis][y+newdis],value[x+newdis][y-newdis],value[x-newdis][y+newdis],value[x-newdis][y-newdis])+random(1.0f);
						diamond(x,y,newdis,1.0f);
					}
				else
					for(int count=0; count<(size/dis)+1; count++){
						System.out.println("square odd");
						int x = index*newdis; int y = (count*2)*newdis;
//						value[x][y]=average(value[x+newdis][y+newdis],value[x+newdis][y-newdis],value[x-newdis][y+newdis],value[x-newdis][y-newdis])+random(1.0f);
						diamond(x,y,newdis,1.0f);
					}
			}
			
			
			dis/=2;
			iter++;
		}
	}
	
	private void diamond(int x, int y, int dis,float rand){
		float avg=0f;
		if(x-dis<0)
			avg+=value[size-dis][y];
		else
			avg+=value[x-dis][y];
		if(y-dis<0)
			avg+=value[x][size-dis];
		else
			avg+=value[x][y-dis];
		if(x+dis>size-1)
			avg+=value[dis][y];
		else
			avg+=value[x+dis][y];
		if(y+dis>size-1)
			avg+=value[x][dis];
		else
			avg+=value[x][y+dis];
		
		value[x][y]=(avg/4)+random(rand);
		
		//		if (x >= 0)
//	    { avg += terrain[x][y + half]; sum += 1.0; }
//	    if (y >= 0)
//	    { avg += terrain[x + half][y]; sum += 1.0; }
//	    if (x + side <= divisions)
//	    { avg += terrain[x + side][y + half]; sum += 1.0; }
//	    if (y + side <= divisions)
//	    { avg += terrain[x + half][y + side]; sum += 1.0; }
//	    terrain[x + half][y + half] = avg / sum + rnd () * scale;
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
	
	public void paint(Graphics g){		
		
		for(int index=0; index<size+1; index++){
			for(int count=0; count<size+1; count++){
				float mul = (((value[index][count]+5)/12));
				
				g.setColor(new Color(1,(int)(150*mul),(int)((214*mul)+40)));
				g.fillRect((index+1)*50,(count+1)*50,45,45);
				
				String temp = new DecimalFormat("0.00").format((double)value[index][count]);
				if(temp.equals("0.00"))
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.RED);
				
				g.drawString(temp,(index+1)*50+13,(count+1)*50+26);
			}
		}
		
//		initArray();
//		repaint();
		
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
