package MJ;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class WhiteDragonTile extends Tile
{
	private static int x = 19;
	private static int y = 3;
	private static int wid = 7;
	private static int flatHeight = 5;
	public String toString()
	{
		return "White Dragon";
	}	
	
	public void paintComponent(Graphics g) 
	{
		if(isRemoved)
		{
			//return;
		}
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 42, 42);
		g.drawRect(24, 8, 32, 32);
		
		for(int i = 0; i<3; i++)
		{
			g.fillRect(x+(i*2*wid), y, wid, flatHeight);
		}
		for(int i = 0; i<3; i++)
		{
			g.fillRect(x+(i*2*wid), 40, wid, flatHeight);
		}
		g.fillRect(x,15, 5, 7);
		
		g.fillRect(x,28, 5, 7);
		
		g.fillRect(x+37,y+5, 5, 7);
		
		g.fillRect(x+37,y+19, 5, 7);
		
		g.fillRect(x+37,y+31, 5, 7);
		
		//draw a shadow
		//figure out why the image is being weird
		//might have to move this to child classes
		//maybe use parent class to adjust shadow shape
		Graphics2D g2 = (Graphics2D) g;
		if(castsUpShadow)
		{
			g2.setColor(Color.black);
			Composite cOld = g2.getComposite();
			Composite cNew = ((AlphaComposite)cOld).derive(.25F);
			
			g2.setComposite(cNew);
					
			g2.setClip(topShadow);
			g2.fillPolygon(topShadow);
			g2.setComposite(cOld);//almost works... still cuts off anywhere not in the shadow
		}	
	}
	
}
