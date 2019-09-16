package MJ;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;





public class BambooTile extends RankTile
{
	
	protected ArrayList<Bamboo> sticks = new ArrayList<>();

	public BambooTile(int rank) 
	{
		super(rank);
		this.setToolTipText(this.toString());
		populateSticks(rank);
	}
	
	private void populateSticks(int rank)
	{
		int wid = 6;
		int halfWid = 3;
		int height = 15;
		
		if(rank == 2)
		{
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y - height-1, Color.blue ));
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y +1, Color.GREEN ));
		}
		else if(rank == 3)
		{
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y - height-1, Color.blue ));
			sticks.add(new Bamboo(Tile.center.x-2*wid, Tile.center.y +1, Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x+wid, Tile.center.y +1, Color.GREEN ));
		}
		else if(rank == 4)
		{
			sticks.add(new Bamboo(Tile.center.x-2*wid,Tile.center.y - height-1, Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+wid, Tile.center.y - height-1, Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x-2*wid, Tile.center.y +1, Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x+wid, Tile.center.y +1, Color.BLUE ));
		}
		else if(rank == 5)
		{
			sticks.add(new Bamboo(Tile.center.x-3*wid,Tile.center.y - height-1, Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y - height-1, Color.BLUE ));
			
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y-height/2, Color.RED));
			
			sticks.add(new Bamboo(Tile.center.x-3*wid, Tile.center.y +1, Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y +1, Color.GREEN ));
		}
		else if(rank == 6)
		{
			sticks.add(new Bamboo(Tile.center.x-3*wid,Tile.center.y - height-1, Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x-halfWid,Tile.center.y - height-1, Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y - height-1, Color.GREEN ));
			
			sticks.add(new Bamboo(Tile.center.x-3*wid, Tile.center.y +1, Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y +1, Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y +1, Color.BLUE ));
		}
		else if(rank == 7)
		{
			//sticks.add(new Bamboo(Tile.center.x-3*wid,Tile.center.y - height-(height/2)-2, Color.RED ));
			sticks.add(new Bamboo(Tile.center.x-halfWid,Tile.center.y - height-(height/2)-2, Color.RED ));
			//sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y - height-(height/2)-2, Color.RED ));
			
			sticks.add(new Bamboo(Tile.center.x-3*wid,Tile.center.y - height+(height/2), Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x-halfWid,Tile.center.y - height+(height/2), Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y - height+(height/2), Color.GREEN ));
			
			sticks.add(new Bamboo(Tile.center.x-3*wid, Tile.center.y +1+(height/2), Color.GREEN ));
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y +1+(height/2), Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y +1+(height/2), Color.GREEN ));
			
		}
		else if(rank == 8)
		{
			
			sticks.add(new Bamboo(Tile.center.x - wid*4+halfWid, Tile.center.y - height-2, Color.GREEN));
			sticks.add(new Bamboo(Tile.center.x - wid*2 -halfWid-1, Tile.center.y - height-2,Math.PI/4 ,Color.GREEN));
			sticks.add(new Bamboo(Tile.center.x + wid + halfWid-1, Tile.center.y - height/2 -height+1,-(Math.PI/4) ,Color.GREEN));
			sticks.add(new Bamboo(Tile.center.x + wid*2+halfWid, Tile.center.y - height-2, Color.GREEN));
			
			sticks.add(new Bamboo(Tile.center.x - wid*4+halfWid, Tile.center.y + height/2-2, Color.BLUE));
			sticks.add(new Bamboo(Tile.center.x -halfWid-2, Tile.center.y,-(Math.PI/4) ,Color.BLUE));
			sticks.add(new Bamboo(Tile.center.x +halfWid-5, Tile.center.y+height/2-2,(Math.PI/4) ,Color.BLUE));			
			sticks.add(new Bamboo(Tile.center.x + wid*2+halfWid, Tile.center.y +height/2-2, Color.BLUE));
//			
//	
		}
		else if(rank == 9)
		{
			sticks.add(new Bamboo(Tile.center.x-3*wid,Tile.center.y - height-(height/2)-2, Color.RED ));
			sticks.add(new Bamboo(Tile.center.x-halfWid,Tile.center.y - height-(height/2)-2, Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y - height-(height/2)-2, Color.GREEN ));
			
			sticks.add(new Bamboo(Tile.center.x-3*wid,Tile.center.y - height+(height/2), Color.RED ));
			sticks.add(new Bamboo(Tile.center.x-halfWid,Tile.center.y - height+(height/2), Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y - height+(height/2), Color.GREEN ));
			
			sticks.add(new Bamboo(Tile.center.x-3*wid, Tile.center.y +1+(height/2), Color.RED ));
			sticks.add(new Bamboo(Tile.center.x-halfWid, Tile.center.y +1+(height/2), Color.BLUE ));
			sticks.add(new Bamboo(Tile.center.x+2*wid, Tile.center.y +1+(height/2), Color.GREEN ));
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//circ1.draw(g);
		for (Bamboo c : sticks)
		{
			if (c != null)
			{
				c.draw(g);
				
				
			}
		}
	}

	public String toString()
	{
		
		//bamboo rank can be 2-9
		return "Bamboo " + rank;
	}
	
	class Bamboo
	{
		protected double theta;
		private Color color;
		protected int x;
		protected int y;
		protected int height;
		protected int width;
		
		Polygon pgg = new Polygon();
		
		{
			
			pgg.addPoint(x+0,y+0);
			pgg.addPoint(x+2, y+3);
			pgg.addPoint(x+0, y+5);			
			pgg.npoints = 3;
		}
		public Bamboo(int x, int y, double theta, Color color)
		{
			this.x = x;
			this.y = y;
			this.theta = theta;
			this.color = color;
			height = 15;
			width = 6;
			
		}
		
		public Bamboo(int x, int y, Color color)
		{			
			this(x,y,0,color);
		}
		
		public void draw(Graphics g)
		{
			if(isRemoved)
			{
				//return;
			}
			Graphics2D g2 = (Graphics2D)g;
			
			
			
			g.setColor(getColor());
			g2.rotate(theta, (x+width), (y+height));
			
			g.fillRoundRect(x, y, width, height, 5, 3);
			g.fillOval(x-2, y, 10, 5);
			g.fillOval(x-2, y+5, 10, 5);
			g.fillOval(x-2, y+10, 10, 5);
			//g.fillRect(x, y, width, height);
			//TODO add some cool stuff
			g.setColor(Color.white);
			g.drawLine(x+4, y+2, x+4, y+10);
			
			g2.rotate(-theta, (x+width), (y+height));
			

			//draw a shadow
			//figure out why the image is being weird
			//might have to move this to child classes
			//maybe use parent class to adjust shadow shape
			if(castsUpShadow)
			{
				//Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				Composite cOld = g2.getComposite();
				Composite cNew = ((AlphaComposite)cOld).derive(.15F);
				
				g2.setComposite(cNew);
						
				g2.setClip(topShadow);
				g2.fillPolygon(topShadow);
				g2.setComposite(cOld);//almost works... still cuts off anywhere not in the shadow
			}
			if(castsRightShadow)
			{
				g2.setColor(Color.black);
				Composite cOld = g2.getComposite();
				Composite cNew = ((AlphaComposite)cOld).derive(.25F);
				
				g2.setComposite(cNew);
						
				g2.setClip(rightShadow);
				g2.fillPolygon(rightShadow);
				g2.setComposite(cOld);//almost works... still cuts off anywhere not in the shadow
			}	
			
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
}
