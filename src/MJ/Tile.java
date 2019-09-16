package MJ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Tile extends JPanel
{
	
	static Polygon pg1 = new Polygon();
	static Polygon pg2 = new Polygon();
	static Polygon pg3 = new Polygon();
	static Polygon pg4 = new Polygon();
	static Polygon topShadow = new Polygon();
	static Polygon rightShadow = new Polygon();
	static Point topLeft = new Point();
	static Point center = new Point();
	static Point botRight = new Point();
	
	static final int WIDTH = 48;
	static final int HEIGHT = 48;
	static final int EDGE = 16;
	
	
	boolean castsUpShadow = false;
	boolean castsRightShadow = false;
	boolean isRemoved = false;
	boolean isSelected = false;
	boolean hintHighlight = false;
	boolean isSelectable = true;
	
	int modelX;
	int modelY;
	int modelZ;
	
	int zOrder;
	
	int posX;
	int posY;
	
	static Rectangle rect;
	
	{
		//create some useful handles
		topLeft.setLocation(16,0);
		center.setLocation(40,24);
		botRight.setLocation(64, 48);
		
		//build top shadow polygon
		topShadow.addPoint(16, 0);
		topShadow.addPoint(64, 0);
		topShadow.addPoint(72, -8);
		topShadow.addPoint(24, -8);		
		topShadow.npoints = 4;
		
		//build right shadow polygon
		rightShadow.addPoint(64, 0);
		rightShadow.addPoint(72, -8);
		rightShadow.addPoint(72, 40);
		rightShadow.addPoint(64, 48);
		rightShadow.npoints = 4;
		
		//build outer left polygon
		pg1.addPoint(0,16);
		pg1.addPoint(0, 64);
		pg1.addPoint(8, 56);
		pg1.addPoint(8, 8);
		pg1.npoints = 4;	
		
		//build outer left polygon
		pg2.addPoint(8, 56);
		pg2.addPoint(8, 8);
		pg2.addPoint(16, 0);
		pg2.addPoint(16, 48);
		pg2.npoints = 4;
		
		//build outer lower polygon
		pg3.addPoint(0, 64);
		pg3.addPoint(48, 64);
		pg3.addPoint(56, 56);
		pg3.addPoint(8, 56);
		pg3.npoints = 4;
		
		//build inner lower polygon
		pg4.addPoint(56, 56);
		pg4.addPoint(8, 56);
		pg4.addPoint(16, 48);
		pg4.addPoint(64, 48);
		pg4.npoints = 4;
		
		//build rectangle
		rect = new Rectangle(16, 0,48,48);
		
	}

	/**
	 * This returns True if the objects are the same class
	 * 
	 * @param other
	 * @return
	 */
	public boolean matches(Tile other)
	{

		// code here
		if (this == other)
		{
			return true;
		}
		if (other == null)
		{
			return false;
		}

		if (other.getClass() != this.getClass())
		{
			return false;
		}

		return true;
	}
	
	public Tile()
	{
		this.setSize(65,64);
		this.setPreferredSize(new Dimension(64, 64));
		this.setToolTipText(this.toString());
		this.setOpaque(false);//so we can see through the whitespace
		
	}
	
	public Point getLocation()
	{
		Point temp = new Point(posX,posY);
		return temp;
	}
	
	public void paintComponent(Graphics g)
	{
		if(isRemoved)
		{
			//return;
		}
		//TODO figure out gradients
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		
		
		
		g.setColor(Color.black);
		GradientPaint grad = new GradientPaint(0, 64, Color.RED,
				64, 0, Color.BLACK);
		if(isSelected)
		{
			grad = new GradientPaint(8, 56, Color.GREEN,
					64, 0, Color.BLACK);
		}
		g2.setPaint(grad);
		
		g2.fillPolygon(pg1);
		g.fillPolygon(pg3);
		
		GradientPaint grad2 = new GradientPaint(8, 56, Color.white,
				64, 0, Color.lightGray);
		if(hintHighlight)
		{
			grad2 = new GradientPaint(8, 56, Color.ORANGE,
					64, 0, Color.lightGray);
		}
		if(isSelected)
		{
			grad2 = new GradientPaint(8, 56, Color.darkGray,
					64, 0, Color.lightGray);
		}
		g2.setPaint(grad2);
		//g.fillPolygon(pg1);
		
		g.fillPolygon(pg2);
		
		//g.fillPolygon(pg3);
		
		g.fillPolygon(pg4);
		
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
				
		
		g.setColor(Color.black);
		g.drawPolygon(pg1);
		g.drawPolygon(pg2);
		g.drawPolygon(pg3);
		g.drawPolygon(pg4);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);	
		
	}
	public void setZOrder()
    {
        zOrder = getParent().getComponentZOrder(this);
    }


    public void resetZOrder()
    {
        getParent().setComponentZOrder(this, zOrder);
    }


    public int getZOrder()
    {
        return zOrder;
    }

}
