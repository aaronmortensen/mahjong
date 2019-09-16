package MJ;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

public class CharacterTile extends Tile
{
	
	protected char symbol;
	private static HashMap<Character, Character> map = new HashMap<>();
	{
		map.put('1', '\u4E00');
		map.put('2', '\u4E8C');
		map.put('3', '\u4E09');
		map.put('4', '\u56DB');
		map.put('5', '\u4E94');
		map.put('6', '\u516D');
		map.put('7', '\u4E03');
		map.put('8', '\u516B');
		map.put('9', '\u4E5D');
		map.put('N', '\u5317');
		map.put('E', '\u6771');
		map.put('W', '\u897F');
		map.put('S', '\u5357');
		map.put('C', '\u4E2D');
		map.put('F', '\u767C');
	}
	
	private static HashMap<Character, Character> flagMap = new HashMap<>();
	{
		flagMap.put('1', 'v');
		flagMap.put('2', 'v');
		flagMap.put('3', 'v');
		flagMap.put('4', 'v');
		flagMap.put('5', 'v');
		flagMap.put('6', 'v');
		flagMap.put('7', 'v');
		flagMap.put('8', 'v');
		flagMap.put('9', 'v');
		flagMap.put('N', 'w');
		flagMap.put('E', 'w');
		flagMap.put('W', 'w');
		flagMap.put('S', 'w');
		flagMap.put('C', 'r');
		flagMap.put('F', 'g');
	}
	
	public String toChinese()
	{
		return map.get(symbol) +"";
	}
	
	public CharacterTile(char symbol)
	{
		this.symbol = symbol;
		this.setToolTipText(this.toString());
	}
	
	/**
	 * returns true of the tiles have the same symbol
	 */
	public boolean matches(Tile other)
	{
		if(super.matches(other)) 
		{
			CharacterTile ct = (CharacterTile)other;
			if(this.symbol == ct.symbol)
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * returns the tiles string
	 */
	public String toString()
	{
		switch(symbol)
		{
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return ("Character " + symbol);
		case 'N':
			return ("North Wind");
		case 'E':
			return ("East Wind");
		case 'W':
			return ("West Wind");
		case 'S': 
			return ("South Wind");
		case 'C':
			return ("Red Dragon");
		case 'F':
			return ("Green Dragon");
			
		default:
			return "CharacterTile toString method switch statement failed somehow";			
		}
	}
	
	
	public void paintComponent(Graphics g) 
	{
		if(isRemoved)
		{
			//return;
		}
		super.paintComponent(g);

		
		Font f = g.getFont();
		//draw symbol on all of them
		g.setColor(Color.RED);
		g.drawString(this.symbol+"", Tile.center.x+11, 12);
		
		//draw wan on numbers
		if(flagMap.get(symbol) == 'v')
		{
			f = f.deriveFont(f.getSize2D() * 1.6F);
			g.setFont(f);
			g.drawString("\u842C", Tile.center.x-10, Tile.center.y+18);
			g.setColor(Color.BLACK);
			g.drawString(this.toChinese(), Tile.center.x-10, Tile.center.y-4);
		}
		else if(flagMap.get(symbol) == 'w')
		{
			g.setColor(Color.BLACK);
			//make font bigger
			f = f.deriveFont(f.getSize2D() * 3F);
			g.setFont(f);
			g.drawString(this.toChinese(), Tile.center.x-20, Tile.center.y+16);
		}
		else if(flagMap.get(symbol) == 'r')
		{
			g.setColor(Color.RED);
			f = f.deriveFont(f.getSize2D() * 3F);
			g.setFont(f);
			g.drawString(this.toChinese(), Tile.center.x-20, Tile.center.y+16);
			
		}
		else if(flagMap.get(symbol) == 'g')
		{
			g.setColor(Color.GREEN);
			f = f.deriveFont(f.getSize2D() * 3F);
			g.setFont(f);
			g.drawString(this.toChinese(), Tile.center.x-20, Tile.center.y+16);
		}
		
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

}
