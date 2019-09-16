package MJ;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class CircleTile extends RankTile
{

	protected ArrayList<MyCircle> circles = new ArrayList<>();
	
	MyCircle circ1;
	
	//eclipse generated most of this code :)
	public CircleTile(int rank) 
	{
		super(rank);	
		this.setToolTipText(this.toString());
		
		populateCircles(this.rank);
		
		
	}
	
	private void populateCircles(int rank)
	{
		//MyCircle(int x, int y, int diameter, Color color)
		int radius = 6;
		if(rank == 1)
		{
			radius = 22;
			circles.add(new Pancake((Tile.center.x-radius), (Tile.center.y-radius), 2*radius, Color.GREEN));
			
			radius = 8;
			circles.add(new MyCircle((Tile.center.x-radius), (Tile.center.y-radius), 2*radius, Color.RED));
			
		}
		else if(rank == 2)
		{
			//change radius
			radius = 10;
			//top circle
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y-(radius*2)), 2*radius , Color.GREEN ));
			
			//bottom circle
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y+(radius/2))-3, 2*radius , Color.RED ));
		}
		else if(rank == 3)
		{
			radius = 7;
			circles.add(new MyCircle((Tile.center.x-(radius*3)), (Tile.center.y-(radius*3)), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y-(radius)), 2*radius , Color.RED ));
			circles.add(new MyCircle((Tile.center.x+(radius)), (Tile.center.y+(radius)), 2*radius , Color.GREEN ));
		}
		else if(rank == 4)
		{
			radius = 10;
			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-1), (Tile.center.y-(radius*2)), 2*radius , Color.BLUE ));			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-1), (Tile.center.y+(radius/2))-3, 2*radius , Color.GREEN ));
			circles.add(new MyCircle((Tile.center.x+(radius/2)-3), (Tile.center.y-(radius*2)), 2*radius , Color.GREEN ));			
			circles.add(new MyCircle((Tile.center.x+(radius/2)-3), (Tile.center.y+(radius/2))-3, 2*radius , Color.BLUE ));
			
		}
		else if(rank == 5)
		{
			radius = 7;
			circles.add(new MyCircle((Tile.center.x-(radius*3)), (Tile.center.y-(radius*3)), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y-(radius)), 2*radius , Color.RED ));
			circles.add(new MyCircle((Tile.center.x+(radius)), (Tile.center.y+(radius)), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x+(radius)), (Tile.center.y-(radius*3)), 2*radius , Color.GREEN ));
			circles.add(new MyCircle((Tile.center.x-(radius*3)), (Tile.center.y+(radius)), 2*radius , Color.GREEN ));
		}
		else if(rank == 6)
		{
			radius = 7;
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y-(radius*3)), 2*radius , Color.GREEN ));			
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y-(radius*3)), 2*radius , Color.GREEN ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y-(radius/2)-4), 2*radius , Color.RED ));			
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y-(radius/2)-4), 2*radius , Color.RED ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y+(radius)), 2*radius , Color.RED ));
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y+(radius)), 2*radius , Color.RED ));		
			
		}
		else if(rank == 7)
		{
			radius = 5;
			
			circles.add(new MyCircle((Tile.center.x-(radius*3)-3), (Tile.center.y-(radius*3)-4), 2*radius , Color.GREEN ));
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y-(radius*2)-4), 2*radius , Color.GREEN ));
			circles.add(new MyCircle((Tile.center.x+(radius)+3), (Tile.center.y-(radius)-4), 2*radius , Color.GREEN ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y+(radius)-4), 2*radius , Color.RED ));			
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y+(radius)-4), 2*radius , Color.RED ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y+(radius*2)+2), 2*radius , Color.RED ));
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y+(radius*2)+2), 2*radius , Color.RED ));
		}
		else if(rank == 8)
		{
			radius = 5;
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y-(radius*4)-2), 2*radius , Color.BLUE ));			
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y-(radius*4)-2), 2*radius , Color.BLUE ));			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y-(radius*2)), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y-(radius*2)), 2*radius , Color.BLUE ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y+(radius)-4), 2*radius , Color.BLUE ));			
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y+(radius)-4), 2*radius , Color.BLUE ));			
			circles.add(new MyCircle((Tile.center.x-(radius*2)-3), (Tile.center.y+(radius*2)+2), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x+(radius)-3), (Tile.center.y+(radius*2)+2), 2*radius , Color.BLUE ));
		}
		else if(rank == 9)
		{
			radius = 7;
			
			circles.add(new MyCircle((Tile.center.x-(radius*3)-1), (Tile.center.y-(radius*3)-1), 2*radius , Color.GREEN ));
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y-(radius*3)-1), 2*radius , Color.GREEN ));
			circles.add(new MyCircle((Tile.center.x+(radius)+1), (Tile.center.y-(radius*3)-1), 2*radius , Color.GREEN ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*3)-1), (Tile.center.y-(radius)), 2*radius , Color.RED ));
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y-(radius)), 2*radius , Color.RED ));
			circles.add(new MyCircle((Tile.center.x+(radius)+1), (Tile.center.y-(radius)), 2*radius , Color.RED ));
			
			circles.add(new MyCircle((Tile.center.x-(radius*3)-1), (Tile.center.y+(radius)+1), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x-(radius)), (Tile.center.y+(radius)+1), 2*radius , Color.BLUE ));
			circles.add(new MyCircle((Tile.center.x+(radius)+1), (Tile.center.y+(radius)+1), 2*radius , Color.BLUE ));
		}
		
	}

	public String toString()
	{
		//rank will be 1-9
		return "Circle " + rank;
	}

	public void paintComponent(Graphics g)
	{
		if(isRemoved)
		{
			//return;
		}
		super.paintComponent(g);
		//circ1.draw(g);
		for (MyCircle c : circles)
		{
			if (c != null)
			{
				Graphics2D g2 = (Graphics2D) g;
				Composite cOld = g2.getComposite();
				Composite cNew = ((AlphaComposite)cOld).derive(.25F);
				c.draw(g);
				
				//draw a shadow
				//figure out why the image is being weird
				//might have to move this to child classes
				//maybe use parent class to adjust shadow shape
				
				g.setColor(Color.black);
				//Composite cOld = g2.getComposite();
				
				g2.setComposite(cNew);
				if(castsUpShadow)
				{
					g2.setClip(topShadow);
					g2.fillPolygon(topShadow);
					
				}	
				if(castsRightShadow)
				{
						
					g2.setClip(rightShadow);
					g2.fillPolygon(rightShadow);
				}
				g2.setComposite(cOld);
				
			}
		}
	}
	
	class MyCircle
	{
		protected int diameter;
		private Color color;
		protected int x;
		protected int y;
		protected int radius;
		Point cent;
		Point tl;
		Point tr;
		Point bl;
		Point br;		
		
		public MyCircle(int x, int y, int diameter, Color color)
		{
			this.x = x;
			this.y = y;
			this.diameter = diameter;
			this.color = color;
			radius = diameter/2;
			cent = new Point(x+radius, y+radius);
			
			int offset = radius/2;
			
			tl = new Point(cent.x - offset , cent.y - offset);
			tr = new Point(cent.x + offset , cent.y - offset);
			bl = new Point(cent.x - offset , cent.y + offset);
			br = new Point(cent.x + offset , cent.y + offset);			
			
		}
		
		public void draw(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g.setColor(getColor());
			g.fillOval(this.x, this.y, diameter, diameter);	
			//TODO add some scratches
			g.setColor(Color.white);			
			g.drawLine(tl.x, tl.y, br.x, br.y);			
			g.drawLine(bl.x, bl.y, tr.x, tr.y);
			
	
		}
		
		private Color getColor()
		{
			//get a darker green
			if(this.color == Color.GREEN)
			{
				this.color = new Color(0,200,0);
			}
			return color;
		}
	}
	
	class Pancake extends MyCircle
	{

		public Pancake(int x, int y, int diameter, Color color)
		{
			super(x, y, diameter, color);
			
		}
		
		@Override
		public void draw(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g.setColor(super.getColor());
			g.fillOval(this.x, this.y, diameter, diameter);
			
			int xPos = Tile.center.x;
			int yPos = y + (diameter/12);
			g.setColor(Color.WHITE);
			for(int i = 0; i <16; i++)
			{
				double theta = ((i)*(Math.PI)/8);
				g2.rotate(theta, Tile.center.x, Tile.center.y);
				g.fillOval(xPos, yPos, 3, 3);
				g2.rotate(-theta, Tile.center.x, Tile.center.y);
			}

		}
		
	}
}
