package MJ;

import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * MahJong game logic will go here
 * 
 * @author Aaron
 *
 */
public class MahJong extends JFrame
{
	private static ScrollTiles removed;

	private static MahJongBoard gameBoard;

	private boolean firstRun = true;

	private int randomSeed = 10000;

	Random rand;

	public MahJong()
	{
		setTitle("MahJong ");

		rand = new Random();

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				exit();
			}
		});
		// backGround = new ImageIcon("images/dragon_bg.png");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu gameMenu = makeMenu("Game", 'G');

		gameMenu.add(makeMenuItem("New Game", "Start a new Game", "ctrl N", 'N', "newGame", this));
		gameMenu.add(makeMenuItem("Restart Game", "Restart the current game", "ctrl S", 'S', "restartGame", this));
		gameMenu.add(makeMenuItem("Toggle Hints", "Toggle Hints", "ctrl H", 'H', "toggleHints", this));
		gameMenu.add(makeMenuItem("Numbered Game", "Start a game with a seed", "ctrl T", 'T', "numberedGame", this));
		gameMenu.addSeparator();
		gameMenu.add(makeMenuItem("Exit", "Exit the program", "ctrl E", 'E', "exit", this));

		JMenu soundMenu = makeMenu("Sound", 'S');
		soundMenu.add(makeMenuItem("toggle sound", "turn the sound off or on", "ctrl O", 'O', "toggleSound", this));

		JMenu moveMenu = makeMenu("Move", 'M');
		moveMenu.add(makeMenuItem("Undo", "Undo last move", "ctrl Z", 'Z', "undoMove", this));
		
		JMenu helpMenu = makeMenu("Help", 'H');
		helpMenu.add(makeMenuItem("Operation", "How to use this program", "ctrl P", 'P', "operation", this));
		helpMenu.add(makeMenuItem("Game Rules", "How to Play MahJong", "ctrl U", 'U', "howToPlay", this));

		menuBar.add(gameMenu);
		menuBar.add(soundMenu);
		menuBar.add(moveMenu);
		menuBar.add(helpMenu);

		newGame();

		setSize(775, 700);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);

		setVisible(true);
	}

	public void exit()
	{
		if (JOptionPane.showConfirmDialog(this, "Do you want to exit game?", "exit game",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
			System.exit(0);
	}
	
	public void operation()
	{
		String message = "Aaron's MahJong\n";
		message += "How to use:\n"
				+ "Restart game: ctrl S\n"
				+ "New Game: ctrl N\n"
				+ "Undo Move: ctrl Z\n"
				+ "Toggle Hints: ctrl H\n"
				+ "Play a seeded game: crtl T\n"
				+ "(allows replay of a specific game)\n"
				+ "Toggle Sound: ctrl O\n\n"
				+ "Version 1.0\n";
				
		//Icon welcomeIcon = new ImageIcon("Welcome.gif");
		JOptionPane.showMessageDialog(
			    null, 
			    message, 
			    "Operations",
			    JOptionPane.INFORMATION_MESSAGE, 
			    null); 
	}
	
	public void howToPlay()
	{
		String message = "Click on the tiles until they are all gone.\n"
				+"click on the first tile then try to find a match\n"
				+"If the second tile matches the First one they will be removed\n"
				+"Tiles match if they are the same type and rank\n"
				+"Or if they are the same characters.\n\n"
				+"Remember you can undo your moves and turn on hints\n"
				+"               Good Luck                      ";
		//Icon welcomeIcon = new ImageIcon("Welcome.gif");
		JOptionPane.showMessageDialog(
			    null, 
			    message, 
			    "How To Play",
			    JOptionPane.INFORMATION_MESSAGE, 
			    null); 
	}

	public void numberedGame()
	{
		String ans = (String) (JOptionPane.showInputDialog(this, "Enter Game Seed", "Bad input will be changed to 15",
				JOptionPane.INFORMATION_MESSAGE, null, null, "[Enter an Integer]"));
		// get number
		try
		{
			randomSeed = Integer.parseInt(ans);
		} catch (NumberFormatException fe)
		{
			randomSeed = 15;
		}

		if (!firstRun)
		{
			remove(gameBoard);
			remove(removed);
		}
		firstRun = false;
		removed = new ScrollTiles();
		gameBoard = new MahJongBoard(removed, randomSeed, this);

		add(gameBoard, BorderLayout.CENTER);
		add(removed, BorderLayout.SOUTH);
		setTitle("MahJong, game sead: " + randomSeed);
		setVisible(true);
	}

	public void toggleSound()
	{
		gameBoard.toggleSound();
	}

	public void toggleHints()
	{
		gameBoard.toggleHints();
		repaint();
	}

	public void undoMove()
	{
		gameBoard.undoMove();
	}

	public void restartGame()
	{
		int response = JOptionPane.showConfirmDialog(null, "Do you want to Restart?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (response == JOptionPane.YES_OPTION)
		{
			remove(gameBoard);
			remove(removed);

			removed = new ScrollTiles();
			gameBoard = new MahJongBoard(removed, randomSeed, this);

			add(gameBoard, BorderLayout.CENTER);
			add(removed, BorderLayout.SOUTH);
			setTitle("MahJong, game sead: " + randomSeed + " open moves: " + gameBoard.countOpenMoves());
			setVisible(true);
		}
	}

	public void newGame()
	{

		int response = JOptionPane.YES_OPTION;
		if(!firstRun)
		{
		response = JOptionPane.showConfirmDialog(null, "Do you want Start a new game?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}

		if (response == JOptionPane.YES_OPTION)
		{
			randomSeed = rand.nextInt(99999);

			if (!firstRun)
			{
				remove(gameBoard);
				remove(removed);
			}
			firstRun = false;
			removed = new ScrollTiles();
			gameBoard = new MahJongBoard(removed, randomSeed, this);

			add(gameBoard, BorderLayout.CENTER);
			add(removed, BorderLayout.SOUTH);
			setTitle("MahJong, game sead: " + randomSeed + " open moves: " + gameBoard.countOpenMoves());
			setVisible(true);
		}
	}

	// these are the factory methods
	private JMenuItem makeMenuItem(String label, String tip, String accelerator, char mnemonic, String method,
			Object target)
	{
		JMenuItem menuItem = new JMenuItem(label, mnemonic);
		menuItem.setToolTipText(tip); // adds tool tip text
		menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator));

		// sets up event handling: "method" is called when "menuItem" is selected
		menuItem.addActionListener((ActionListener) EventHandler.create(ActionListener.class, target, method));

		return menuItem;
	}

	private JMenu makeMenu(String label, char mnemonic)
	{
		JMenu menu = new JMenu(label);
		menu.setMnemonic(mnemonic);
		return menu;
	}

	public static void main(String[] args)
	{
		new MahJong();
	}
}
