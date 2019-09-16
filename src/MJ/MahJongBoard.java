package MJ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * MahJongBoard extends JPanel and displays all the tiles
 * 
 * @author Aaron it added my name. neat.
 */
public class MahJongBoard extends JPanel implements MouseListener
{
	// height - (8*48+16) width - (15*48+16)

	private ImageIcon backGround; // swing

	/**
	 * deck is used to shuffle and deal he tiles
	 */
	TileDeck deck;

	private Tile first = null;
	private Tile second = null;

	private boolean hintsOn = true;
	
	private int seed;

	private int score;

	/**
	 * 3d array used to represent the game board
	 */
	Tile[][][] tiles;

	Fireworks reward;

	boolean sound = true;

	private ScrollTiles removed;
	
	private String baseTitle;

	private PlayClip clip = new PlayClip("/sounds/stone-scraping.wav", true);
	
	private MahJong mahJong;

	/**
	 * MahJongBoard default constructor should paint the jpanel with tiles
	 * 
	 * @param randomSeed
	 * @param mahJong 
	 */
	public MahJongBoard(ScrollTiles removed, int randomSeed, MahJong mahJong)
	{
		this.removed = removed;
		this.seed = randomSeed;
		// initialize and shuffle deck of tiles
		deck = new TileDeck();
		deck.shuffle(randomSeed);
		int totalRows = 8;
		int totalColumns = 15;
		int totalLayers = 5;
		tiles = new Tile[totalRows][totalColumns][totalLayers];
		
		this.mahJong = mahJong;

		URL url = MahJong.class.getResource("/images/dragon_bg.png");
		backGround = new ImageIcon(url);

		// removed.addToUndo(new FlowerTile("Plum"), new FlowerTile("Bamboo"));

		// set layout to null
		setLayout(null);

		score = 0;
		pupulateTileArray();

		addTileArray();

		Color color = new Color(100, 100, 75);

		// add(removed);
		setBackground(color);
		baseTitle = mahJong.getTitle();
		mahJong.setTitle("MahJong, game sead: " + seed + " open moves: " + countOpenMoves());
		setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backGround.getImage(), 15, 15, this);
	}

	private void addTileArray()
	{
		for (int layer = 4; layer >= 0; layer--)
		{
			for (int column = 0; column < 15; column++)
			{
				for (int row = 7; row >= 0; row--)
				{
					Tile t = tiles[row][column][layer];
					if (t == null)
					{
						continue;
					}

					calculateLocation(t);
					t.setLocation(t.posX, t.posY);
					t.addMouseListener(this);
					// System.out.println(t.posX + " " + t.posY + " " + layer);
					add(t, getComponentZOrder(t));
					t.zOrder = getComponentZOrder(t);					
				}
			}
		}

	}

	private void pupulateTileArray()
	{
		Tile t;
		for (int layer = 0; layer < 5; layer++)
		{
			int layerRows = 0;
			int rowColumns = 0;
			int startRow = 0;
			int startColumn = 0;
			switch (layer)
			{
			case 0:
				layerRows = 8;
				startRow = 0;

				break;
			case 1:
				layerRows = 6;
				rowColumns = 6;
				startRow = 1;
				startColumn = 4;
				break;
			case 2:
				layerRows = 4;
				rowColumns = 4;
				startRow = 2;
				startColumn = 5;
				break;
			case 3:
				layerRows = 2;
				rowColumns = 2;
				startRow = 3;
				startColumn = 6;
				break;
			case 4:
				layerRows = 1;
				rowColumns = 1;
				startRow = 3;
				startColumn = 7;
				break;
			}

			for (int row = startRow; row < (layerRows + startRow); row++)
			{
				if (layer == 0)
				{
					switch (row)
					{
					case 0:
					case 7:
					case 4:
						rowColumns = 12;
						startColumn = 1;
						break;
					case 1:
					case 6:
						rowColumns = 8;
						startColumn = 3;
						break;
					case 2:
					case 5:
						rowColumns = 10;
						startColumn = 2;
						break;
					case 3:
						rowColumns = 15;
						startColumn = 0;
						break;
					}
				}
				for (int column = startColumn; column < (rowColumns + startColumn); column++)
				{
					t = deck.deal();
					positionTile(t, row, column, layer);
					//System.out.println(column + " " + layer);
					t.isRemoved = false;
					
					tiles[row][column][layer] = t;

					// setShadows();
				}
			}
		}
		// setShadows();
	}



	public void positionTile(Tile t, int y, int x, int z)
	{
		t.modelX = x;
		t.modelY = y;
		t.modelZ = z;
	}

	// it works!
	// Now it works correctly!!!
	private boolean isTileOpen(int x, int y, int z)
	{
		if (x == 0 || x == 14 || z == 4)
			return true;

		boolean top = false;
		boolean right = false;
		boolean left = false;
		// i made it y, x, z for some dumb reason
		top = ((tiles[y][x][z + 1] == null || tiles[y][x][z + 1].isRemoved));
		left = ((tiles[y][x - 1][z] == null || tiles[y][x - 1][z].isRemoved));
		right = ((tiles[y][x + 1][z] == null) || (tiles[y][x + 1][z].isRemoved));

		// handle special cases...
		if (z == 0 && x == 1 && y == 4)
		{
			if (tiles[3][0][0] != null)
				if (!tiles[3][0][0].isRemoved)
					left = false;
		}
		if (z == 3)
		{
			if (tiles[3][7][4] != null)
				if (!tiles[3][7][4].isRemoved)
				{
					top = false;
				}
		}
		if (x == 12 && y == 4 && z == 0)
		{
			if (tiles[3][13][0] != null)
				if (!tiles[3][13][0].isRemoved)
				{
					right = false;
				}
		}
		if (x == 13 && y == 3 && z == 0)
		{
			if (tiles[4][12][0] != null)
				if (!tiles[4][12][0].isRemoved)
				{
					left = false;
				}
		}
		return (top && (left || right));
	}

	// calculate the tiles pixel location
	public void calculateLocation(Tile t)
	{
		// x = ±col * WIDTH ± layer * EDGE
		// y = ±row * HEIGHT ± layer * EDGE
		t.posX = t.modelX * Tile.WIDTH + t.modelZ * Tile.EDGE;
		t.posX += 8;

		t.posY = t.modelY * Tile.HEIGHT - t.modelZ * Tile.EDGE + 8;
		t.posY += 24;
		if (t.modelZ > 3)
		{
			t.posX -= 48;
		}

		// handle special cases
		if (t.modelZ == 4)
		{
			t.posY += 24;
			t.posX += 24;
		}
		if (t.modelX == 0 || t.modelX == 13 || t.modelX == 14)
		{
			t.posY += 24;
		}
		//System.out.println(t.modelX + " " + t.modelY + " " + t.modelZ);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		validate();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{

		Tile temp = (Tile) e.getSource(); 
		System.out.println("selecing x:"+temp.modelX + " y:" + temp.modelY + " z:" + temp.modelZ+ " order:" +temp.zOrder);
		if (!temp.isSelectable)
		{
			return;
		}
		if (!isTileOpen(temp.modelX, temp.modelY, temp.modelZ))
		{
			return;
		}
		
		if (first == null) 
		{
			first = temp;
			first.isSelected = true;
			if (this.hintsOn)
			{
				highlightHinted(true);
			}
			repaint();
			// first.validate();
			return;
		} else if (temp == first) 
		{
			first.isSelected = false;
			first = null;
			highlightHinted(false);
			repaint();
			return;
		} else
		{
			second = temp;
		}

		
		if (first.matches(second))
		{
			removeTiles(first, second);
			
			first.isSelected = second.isSelected = false;
			first = second = null;
			if(sound)
				clip.play();
			
		} else
		{
			second.isSelected = false;
			second = null;
		}
		
		// startReward();
		// second.isSelected = first.isSelected = false;
		mahJong.setTitle("MahJong, game sead: " + seed + " open moves: " + countOpenMoves());
		highlightHinted(false);
		repaint();
	}
	
	public int countOpenMoves()
	{
		int movesLeft = 0;
		
		for (int layer = 4; layer >= 0; layer--)
		{
			for (int column = 0; column <= 14; column++)
			{
				for (int row = 7; row >= 0; row--)
				{
					Tile t = tiles[row][column][layer];
					if(t != null)
					{
						if(isTileOpen(t.modelX, t.modelY, t.modelZ) && hasOpenMatch(t) && (t.isRemoved == false))
						{
							movesLeft++;
						}
					}
				}
			}
		}	
		return movesLeft/2;
	}

	private boolean hasOpenMatch(Tile f)
	{
		
		// TODO Auto-generated method stub
		for (int layer = 4; layer >= 0; layer--)
		{
			for (int column = 0; column <= 14; column++)
			{
				for (int row = 7; row >= 0; row--)
				{
					Tile t = tiles[row][column][layer];
					if(t!= null)
					{
						if(isTileOpen(t.modelX, t.modelY, t.modelZ) && isTileOpen(f.modelX, f.modelY, f.modelZ) 
								&& f.matches(t) && (f != t) && (t.isRemoved == false))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private void highlightHinted(boolean highlight)
	{
		for (int layer = 4; layer >= 0; layer--)
		{
			for (int column = 0; column <= 14; column++)
			{
				for (int row = 7; row >= 0; row--)
				{
					Tile t = tiles[row][column][layer];
					if (t != null)
					{
						t.hintHighlight = false;
					}

					if (t != null && t.matches(first) && isTileOpen(t.modelX, t.modelY, t.modelZ))
					{
						t.hintHighlight = highlight;
					}
				}
			}
		}
	}

	private void repaintTiles()
	{
		for (int layer = 4; layer >= 0; layer--)
		{
			for (int column = 0; column <= 14; column++)
			{
				for (int row = 7; row >= 0; row--)
				{
					Tile t = tiles[row][column][layer];
					if (t != null)
					{
						t.repaint();
					}
					
				}
			}
		}
	}
	
	private void startReward()
	{
		// check score

		reward = new Fireworks(this);
		reward.setSound(sound);
		reward.fire();
		JOptionPane.showMessageDialog(
			    null, 
			    "You WON!!!", 
			    "Winner!",
			    JOptionPane.INFORMATION_MESSAGE, 
			    null); 
	}

	private void stopReward()
	{
		if (reward == null)
			return;
		reward.stop();
		reward = null;
	}

	public void undoMove()
	{
		if (score >= 2)
		{
			if(first!= null)//undo any selections if undoing a move
			{
				first.isSelected = false;
				first = null;
			}
//			if(second.isSelected) 
//				return;
			
			Tile t1 = removed.getFromUndo(0, this);
			Tile t2 = removed.getFromUndo(1, this);
			
			if(t1.zOrder > t2.zOrder)
			{
				Tile temp = t1;
				t1 = t2;
				t2 = temp;
			}
			
			t1.isRemoved = t2.isRemoved = false;
			t1.isSelectable = t2.isSelectable = true;
			
			
			
			
			
			//positionTile(t1, t1.modelY, t1.modelX, t1.modelZ);
			calculateLocation(t1);			
			t1.setLocation(t1.posX, t1.posY);
			tiles[t1.modelY][t1.modelX][t1.modelZ] = t1;
			System.out.println("about to add");
			add(t1);
			
			setComponentZOrder(t1, t1.zOrder);
			setComponentZOrder(t1, t1.zOrder);
			System.out.println("readding x:"+t1.modelX + " y:" + t1.modelY + " z:" + t1.modelZ + " order:" +t1.zOrder);		
			
			//positionTile(t2, t2.modelY, t2.modelX, t2.modelZ);
			calculateLocation(t2);
			t2.setLocation(t2.posX, t2.posY);
			tiles[t2.modelY][t2.modelX][t2.modelZ] = t2;
			System.out.println("about to add");
			
			
			add(t2);
			
			System.out.println("about to set z order");
			setComponentZOrder(t2, t2.zOrder);
			System.out.println("readding x:"+t2.modelX + " y:" + t2.modelY + " z:" + t2.modelZ + " order:" +t2.zOrder);


			

			this.repaintTiles();

			score -= 2;

			
			mahJong.setTitle("MahJong, game sead: " + seed + " open moves: " + countOpenMoves());
			highlightHinted(false);
			repaint();
			validate();
		
		}
	}

	public void toggleSound()
	{
		sound = !sound;
		
	}
	public void toggleHints()
	{
		hintsOn = !hintsOn;
		highlightHinted(hintsOn);
	}

	private void removeTiles(Tile e, Tile f)
	{

		
		e.setZOrder();
		f.setZOrder();
		
		System.out.println("removing x:"+e.modelX + " y:" + e.modelY + " z:" + e.modelZ + " order:" +e.zOrder);		
		System.out.println("removing x:"+f.modelX + " y:" + f.modelY + " z:" + f.modelZ + " order:" +f.zOrder);
		
		
		
		
		removed.addToUndo(e, f);

		e.isSelected = f.isSelected = false;
		e.isSelectable = f.isSelectable = false;
		e.isRemoved = f.isRemoved = true;

		e.repaint();
		f.repaint();

		score += 2;

		if (score == 144)
		{
			playerWon();
		}
		mahJong.setTitle("MahJong, game sead: " + seed + " open moves: " + countOpenMoves());
		highlightHinted(false);// maybe dont need
		validate();

		// add pieces to removed stack
	}

	private void playerWon()
	{
		startReward();
	}



	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	

}