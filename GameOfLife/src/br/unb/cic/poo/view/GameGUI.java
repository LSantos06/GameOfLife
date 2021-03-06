package br.unb.cic.poo.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.unb.cic.poo.engine.GameEngine;
import br.unb.cic.poo.game.Cell;
import br.unb.cic.poo.game.Statistics;
import br.unb.cic.poo.rules.Rules;
import br.unb.cic.poo.rules.Strategy;

public class GameGUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -5713208627727048620L;
	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
	private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
	private static final int BLOCK_SIZE = 10;

	// Dependency Injection
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("game.xml");

	private JMenuBar menu;
	private JMenu game, rule, statistics;

	private JMenuItem gamePlay, gameStop, gameReset, gameNextGeneration, gameMovesPerSecond, gameAutoFill, gameExit;

	private Rules rules;
	private ArrayList<JMenuItem> ruleList = new ArrayList<JMenuItem>(0);

	private JMenuItem statisticsView;

	private int movesPerSecond = 10;

	private GameBoard gameBoard;
	private Thread gameOfLife;

	private GameEngine engine;

	public static void main(String[] args) {
		// Initializing Statistics
		Statistics gameStatistics = new Statistics();

		//Initializing Engine
		GameEngine engine = new GameEngine(gameStatistics);
		// Default strategy
		Rules initialRule = (Rules)context.getBean("rules");
		engine.setStrategy((Strategy)context.getBean(initialRule.getStrategies().get(0).getBeanName()));

		// Setup the swing specifics
		JFrame game = new GameGUI(engine);

		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Conway's Game of Life (by Lucas & Gabriel)");
		game.setSize(DEFAULT_WINDOW_SIZE);
		game.setMinimumSize(MINIMUM_WINDOW_SIZE);
		game.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - game.getWidth())/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - game.getHeight())/2);
		game.setVisible(true);
	}

	public GameGUI(GameEngine engine){
		// Setting up the menu
		menu = new JMenuBar();
		setJMenuBar(menu);

		// Menu columns
		game = new JMenu("Game");
		menu.add(game);

		rule = new JMenu("Rule");
		menu.add(rule);

		statistics = new JMenu("Statistics");
		menu.add(statistics);

		// Sub-menu of the column Game
		gamePlay = new JMenuItem("Play");
		gamePlay.addActionListener(this);

		gameStop = new JMenuItem("Stop");
		gameStop.setEnabled(false);
		gameStop.addActionListener(this);

		gameReset = new JMenuItem("Reset");
		gameReset.addActionListener(this);
		
		gameNextGeneration = new JMenuItem("Next Generation");
		gameNextGeneration.addActionListener(this);

		gameMovesPerSecond = new JMenuItem("Moves per second");
		gameMovesPerSecond.addActionListener(this);

		gameAutoFill = new JMenuItem("Autofill");
		gameAutoFill.addActionListener(this);

		gameExit = new JMenuItem("Exit");
		gameExit.addActionListener(this);

		game.add(gamePlay);
		game.add(gameStop);
		game.add(gameReset);
		game.add(new JSeparator());
		game.add(gameNextGeneration);
		game.add(new JSeparator());
		game.add(gameMovesPerSecond);
		game.add(gameAutoFill);
		game.add(gameExit);       

		// Sub-menu of the column Rule
		rules = (Rules)context.getBean("rules");

		// For each rule, add a menu item
		for(Strategy currentRule: rules.getStrategies()){
			// Add the name of the strategy
			JMenuItem item = new JMenuItem(currentRule.getName());
			
			// Set the item name to the bean associated to the strategy
			item.setName(currentRule.getBeanName());
			
			// Add the rule in the list of rule menu items
			ruleList.add(item);
		}
		for(JMenuItem currentItem: ruleList){
			currentItem.addActionListener(this);
			rule.add(currentItem);
		}

		// Sub-menu of the column Statistics
		statisticsView = new JMenuItem("View");
		statisticsView.addActionListener(this);

		statistics.add(statisticsView);

		// Setup game board
		gameBoard = new GameBoard();
		add(gameBoard);    	

		// Setting Engine
		this.engine = engine;
	}

	public void setGameBeingPlayed(boolean isBeingPlayed) {
		if (isBeingPlayed) {
			gamePlay.setEnabled(false);
			gameStop.setEnabled(true);
			
			gameNextGeneration.setEnabled(false);

			gameOfLife = new Thread(gameBoard);
			gameOfLife.start();
		} else {
			gamePlay.setEnabled(true);
			gameStop.setEnabled(false);
			
			gameNextGeneration.setEnabled(true);

			gameOfLife.interrupt();
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// Sub-menu Game
		if (actionEvent.getSource().equals(gamePlay)){
			// Play the game
			setGameBeingPlayed(true);

		} else if (actionEvent.getSource().equals(gameStop)){
			// Stop the game
			setGameBeingPlayed(false);

		} else if (actionEvent.getSource().equals(gameReset)){
			// Reset the game
			engine.getStatistics().setKilledCells(0);
			engine.getStatistics().setRevivedCells(0);
			gameBoard.resetBoard();
			gameBoard.repaint();
			
		} else if (actionEvent.getSource().equals(gameNextGeneration)){
			// Reset the values of the Statistics
			engine.getStatistics().setKilledCells(0);
			engine.getStatistics().setRevivedCells(0);
			// Computes the next generation
			setGameBeingPlayed(true);
			setGameBeingPlayed(false);
			
		} else if (actionEvent.getSource().equals(gameMovesPerSecond)){
			// Defines the moves per second
			final JFrame frameGameMovesPerSecond = new JFrame();
			frameGameMovesPerSecond.setTitle("Moves Per Second");
			frameGameMovesPerSecond.setSize(300,60);
			frameGameMovesPerSecond.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frameGameMovesPerSecond.getWidth())/2, 
					(Toolkit.getDefaultToolkit().getScreenSize().height - frameGameMovesPerSecond.getHeight())/2);
			frameGameMovesPerSecond.setResizable(false);

			JPanel panelGameMovesPerSecond = new JPanel();
			panelGameMovesPerSecond.setOpaque(false);

			frameGameMovesPerSecond.add(panelGameMovesPerSecond);

			panelGameMovesPerSecond.add(new JLabel("Number of moves per second:"));

			Integer[] secondOptions = {1,5,10,15,25,50,100};	
			final JComboBox<Integer> comboBoxSeconds = new JComboBox<Integer>(secondOptions);

			panelGameMovesPerSecond.add(comboBoxSeconds);

			comboBoxSeconds.setSelectedItem(movesPerSecond);
			comboBoxSeconds.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					movesPerSecond = (Integer)comboBoxSeconds.getSelectedItem();
					frameGameMovesPerSecond.dispose();
				}
			});
			frameGameMovesPerSecond.setVisible(true);

		} else if (actionEvent.getSource().equals(gameAutoFill)){
			// Generates cells randomly
			final JFrame frameAutoFill = new JFrame();
			frameAutoFill.setTitle("AutoFill");
			frameAutoFill.setSize(360,60);
			frameAutoFill.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frameAutoFill.getWidth())/2, 
	                (Toolkit.getDefaultToolkit().getScreenSize().height - frameAutoFill.getHeight())/2);
			frameAutoFill.setResizable(false);
			
			JPanel panelAutoFill = new JPanel();
			panelAutoFill.setOpaque(false);
			
			frameAutoFill.add(panelAutoFill);
			
			panelAutoFill.add(new JLabel("What percentage should be filled? "));
			
			Object[] percentageOptions = {"Select",5,10,15,20,25,30,40,50,60,70,80,90,95};
			final JComboBox<Object> comboBoxPercent = new JComboBox<Object>(percentageOptions);
			
			panelAutoFill.add(comboBoxPercent);
			
			comboBoxPercent.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if(comboBoxPercent.getSelectedIndex() > 0){
						gameBoard.resetBoard();
						gameBoard.randomlyFillBoard((Integer)comboBoxPercent.getSelectedItem());
						frameAutoFill.dispose();
					}
				}			
			});
			frameAutoFill.setVisible(true);

		} else if (actionEvent.getSource().equals(gameExit)){
			// Exit the game
			System.exit(0);
		}

		// Sub-menu Statistics
		else if (actionEvent.getSource().equals(statisticsView)){
			// Statistics
			final JFrame frameStatistics = new JFrame();
			frameStatistics.setTitle("Statistics");
			frameStatistics.setSize(360,60);
			frameStatistics.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frameStatistics.getWidth())/2, 
	                (Toolkit.getDefaultToolkit().getScreenSize().height - frameStatistics.getHeight())/2);
			frameStatistics.setResizable(false);
			
			JPanel panelStatistics = new JPanel();
			panelStatistics.setOpaque(false);
			
			frameStatistics.add(panelStatistics);
			
			panelStatistics.add(new JLabel("Revived Cells:"));
			panelStatistics.add(new JLabel(String.valueOf(engine.getStatistics().getRevivedCells())));
			panelStatistics.add(new JLabel("Killed Cells:"));
			panelStatistics.add(new JLabel(String.valueOf(engine.getStatistics().getKilledCells())));
			
			frameStatistics.setVisible(true);
		}

		// Sub-menu Rule
		else {
			for(int index = 0; index < ruleList.size(); index++){
				// Make the rule enabled
				ruleList.get(index).setEnabled(true);

				if (actionEvent.getSource().equals(ruleList.get(index))){
					// Set the strategy using the bean name in the list of rule menu items
					engine.setStrategy((Strategy)context.getBean(ruleList.get(index).getName()));
					
					// Make the rule disabled
					ruleList.get(index).setEnabled(false);
				}
			}
		}
	}

	private class GameBoard extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, Runnable {
		private static final long serialVersionUID = 7981483545957168838L;
		private Dimension gameBoardSize = null;
		private ArrayList<Cell> cell = new ArrayList<Cell>(0);

		public GameBoard() {
			// Add resizing listener
			addComponentListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		private void updateArraySize() {
			ArrayList<Cell> removeList = new ArrayList<Cell>(0);
			for (Cell current : cell) {
				if ((current.getX() > gameBoardSize.width-1) || (current.getY() > gameBoardSize.height-1)) {
					removeList.add(current);
				}
			}
			cell.removeAll(removeList);
			repaint();
		}

		public void addCell(int x, int y) {
			if (!cell.contains(new Cell(x,y))) {
				cell.add(new Cell(x,y));
			} 
			repaint();
		}

		public void addCell(MouseEvent mouseEvent) {
			int x = mouseEvent.getPoint().x/BLOCK_SIZE-1;
			int y = mouseEvent.getPoint().y/BLOCK_SIZE-1;
			if ((x >= 0) && (x < gameBoardSize.width) && (y >= 0) && (y < gameBoardSize.height)) {
				addCell(x,y);
			}
		}

		public void resetBoard() {
			cell.clear();
		}

		public void randomlyFillBoard(int percent) {
			for (int i=0; i<gameBoardSize.width; i++) {
				for (int j=0; j<gameBoardSize.height; j++) {
					if (Math.random()*100 < percent) {
						addCell(i,j);
					}
				}
			}
		}

		@Override
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);

			try {
				for (Cell newCell : cell) {
					// Draw new point
					graphics.setColor(Color.darkGray);
					//TODO Error
					graphics.fillRect(BLOCK_SIZE + (BLOCK_SIZE*newCell.getX()), BLOCK_SIZE + (BLOCK_SIZE*newCell.getY()), BLOCK_SIZE, BLOCK_SIZE);
				}
			} catch (ConcurrentModificationException cme) {}

			// Setup grid
			graphics.setColor(Color.BLACK);
			for (int i=0; i<=gameBoardSize.width; i++) {
				graphics.drawLine(((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE, (i*BLOCK_SIZE)+BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE*gameBoardSize.height));
			}
			for (int i=0; i<=gameBoardSize.height; i++) {
				graphics.drawLine(BLOCK_SIZE, ((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE*(gameBoardSize.width+1), ((i*BLOCK_SIZE)+BLOCK_SIZE));
			}
		}

		@Override
		public void componentResized(ComponentEvent componentEvent) {
			// Setup the game board size with proper boundries
			gameBoardSize = new Dimension(getWidth()/BLOCK_SIZE-2, getHeight()/BLOCK_SIZE-2);
			updateArraySize();
		}

		@Override
		public void componentMoved(ComponentEvent ce) {}
		@Override
		public void componentShown(ComponentEvent ce) {}
		@Override
		public void componentHidden(ComponentEvent ce) {}
		@Override
		public void mouseClicked(MouseEvent me) {}
		@Override
		public void mousePressed(MouseEvent me) {}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			// Mouse was released (user clicked)
			addCell(mouseEvent);
		}

		@Override
		public void mouseEntered(MouseEvent me) {}
		@Override
		public void mouseExited(MouseEvent me) {}

		@Override
		public void mouseDragged(MouseEvent mouseEvent) {
			// Mouse is being dragged, user wants multiple selections
			addCell(mouseEvent);
		}

		@Override
		public void mouseMoved(MouseEvent me) {}

		@Override
		public void run() {
			// Initializing the board
			boolean[][] gameBoard = new boolean[gameBoardSize.width+2][gameBoardSize.height+2];
			for (Cell current : cell) {
				gameBoard[current.getX()+1][current.getY()+1] = true;
			}	            
			
			// Creating the list of survivingCells
			ArrayList<Cell> survivingCells = new ArrayList<Cell>(0);

			// Getting the strategy
			survivingCells = engine.getStrategy().survivors(gameBoard, survivingCells, engine);
			
			// Adding the survivingCells to the board
			resetBoard();
			cell.addAll(survivingCells);
			repaint();

			try {
				Thread.sleep(1000/movesPerSecond);
				run();
			} catch (InterruptedException ie) {}

		}
	}
}
