package MJ;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public abstract class PictureTile extends Tile
{
	private String name;
	private String fileName;
	private ImageIcon image;
	public PictureTile(String name)
	{
		this.name = name;
		
		fileName = "/images/" + name + ".png";
	}
	
	
	public void paintComponent(Graphics g) 
	{
		if(isRemoved)
		{
			//return;
		}
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		URL	url = MahJong.class.getResource(fileName);
		image = new ImageIcon(url);
		
		//image = new ImageIcon(fileName);
		
		image = new ImageIcon(image.getImage().getScaledInstance((int)(image.getIconWidth() * .75), -1, Image.SCALE_SMOOTH));

		image.paintIcon(this, g, 17, 3);
		
		//draw a shadow
		//figure out why the image is being weird
		//might have to move this to child classes
		//maybe use parent class to adjust shadow shape
		
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

	public String toString()
	{
		//"Chrysanthemum", "Orchid", "Plum", "Bamboo", "Spring", "Summer", "Fall", or "Winter".

		return name;
	}
}
