package MJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ScrollTiles extends JScrollPane
{
	private		Dimension	size = new Dimension(Tile.HEIGHT + Tile.EDGE +10,
							Tile.HEIGHT + 2 * Tile.EDGE + 10);

	private	JPanel[]	discard = new JPanel[2];
	private	Stack<Tile>	undoStack = new Stack<Tile>();
	//private	Stack<Tile>	redoStack = new Stack<Tile>();		// optional
	private		int		width = Tile.HEIGHT + Tile.EDGE;// + 6;
	private		int		height = Tile.HEIGHT + Tile.EDGE;
	private		int		count = 0;
	

	public ScrollTiles()
	{
		setPreferredSize(new Dimension(65, 2 * height + 33));
		setBorder(BorderFactory.createRaisedBevelBorder());

		discard[0] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		discard[1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		discard[0].setPreferredSize(new Dimension(65, height+1));
		discard[1].setPreferredSize(new Dimension(65, height+1));

		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JPanel	panel = new JPanel(new BorderLayout());
		setViewportView(panel);

		panel.add(discard[0], BorderLayout.NORTH);
		panel.add(discard[1], BorderLayout.SOUTH);

		discard[0].setBackground(new Color(0, 0, 0));
		discard[1].setBackground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 0));
	}

	public Tile getFromUndo(int whichOne, MahJongBoard mahJongBoard)
	{
		//System.out.println(undoStack.size());
		if(undoStack.size() == 0)
		{
			return null;
		}
		Tile t = undoStack.pop();
		
		

		
		discard[whichOne].remove(0);		
		discard[whichOne].repaint();
		
		Rectangle	r = new Rectangle(0, 0, width, height + 6);
		getViewport().scrollRectToVisible(r);
		return t;
	}
	
	

	public void addToUndo(Tile t1, Tile t2)
	{
		
		
		undoStack.push(t1);
		undoStack.push(t2);
		
		

		Dimension	size = new Dimension(++count * width, height + 6);
		discard[0].setPreferredSize(size);
		discard[1].setPreferredSize(size);

		

		Rectangle	r = new Rectangle(0, 0, width, height + 6);
		getViewport().scrollRectToVisible(r);


		
		discard[0].add(t1, 0);
		discard[1].add(t2, 0);
		

		revalidate();
		repaint();
		//System.out.println(undoStack.size());
	}
}
