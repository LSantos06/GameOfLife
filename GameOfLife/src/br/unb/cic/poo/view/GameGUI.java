package br.unb.cic.poo.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.unb.cic.poo.controller.GameController;
import br.unb.cic.poo.engine.Rules;
import br.unb.cic.poo.engine.Strategy;
import br.unb.cic.poo.game.Cell;
import br.unb.cic.poo.game.Statistics;
import br.unb.cic.poo.model.GameEngine;

public class GameGUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -5713208627727048620L;
	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
	private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
	private static final int BLOCK_SIZE = 10;

	// Dependency Injection
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("game.xml");

	private JMenuBar menu;
	private JMenu game, rule, statistics;

	private JMenuItem gamePlay, gameStop, gameReset, gameMovesPerSecond, gameAutofill, gameExit;

	private Rules rules;
	private ArrayList<JMenuItem> ruleList = new ArrayList<JMenuItem>(0);

	private JMenuItem statisticsView;

	private int movesPerSecond = 10;

	private GameBoard gameBoard;
	private Thread gameOfLife;

	private GameEngine engine;
	private GameController controller;

	public static void main(String[] args) {
		// Initializing Statistics
		Statistics gameStatistics = new Statistics();

		// Initializing Controller and Engine
		GameController controller = new GameController();

		//TODO
		GameEngine engine = new GameEngine(10, 10, gameStatistics);
		// Default strategy: conway
		engine.setStrategy((Strategy)context.getBean("conway"));

		// Setup the swing specifics
		JFrame game = new GameGUI(controller, engine);

		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Conway's Game of Life");
		game.setSize(DEFAULT_WINDOW_SIZE);
		game.setMinimumSize(MINIMUM_WINDOW_SIZE);
		game.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - game.getWidth())/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - game.getHeight())/2);
		game.setVisible(true);
	}

	public GameGUI(GameController controller, GameEngine engine){
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

		gameMovesPerSecond = new JMenuItem("Moves per second");
		gameMovesPerSecond.addActionListener(this);

		gameAutofill = new JMenuItem("Autofill");
		gameAutofill.addActionListener(this);

		gameExit = new JMenuItem("Exit");
		gameExit.addActionListener(this);

		game.add(gamePlay);
		game.add(gameStop);
		game.add(gameReset);
		game.add(new JSeparator());
		game.add(gameMovesPerSecond);
		game.add(gameAutofill);
		game.add(gameExit);       

		// Sub-menu of the column Rule
		rules = (Rules)context.getBean("rules");

		// For each rule, add a button
		for(Strategy currentRule: rules.getStrategies()){
			JMenuItem item = new JMenuItem(currentRule.getName());
			item.setName(currentRule.getBeanName());
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

		// Setting Controller and Engine
		this.controller = controller;
		this.engine = engine;
	}

	public void setGameBeingPlayed(boolean isBeingPlayed) {
		if (isBeingPlayed) {
			gamePlay.setEnabled(false);
			gameStop.setEnabled(true);

			gameOfLife = new Thread(gameBoard);
			gameOfLife.start();
		} else {
			gamePlay.setEnabled(true);
			gameStop.setEnabled(false);

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
			gameBoard.resetBoard();
			gameBoard.repaint();

		} else if (actionEvent.getSource().equals(gameMovesPerSecond)){
			// Defines the moves per second
			final JFrame frameGameMovesPerSecond = new JFrame();
			frameGameMovesPerSecond.setTitle("Game");
			frameGameMovesPerSecond.setSize(300,60);
			frameGameMovesPerSecond.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frameGameMovesPerSecond.getWidth())/2, 
					(Toolkit.getDefaultToolkit().getScreenSize().height - frameGameMovesPerSecond.getHeight())/2);
			frameGameMovesPerSecond.setResizable(false);

			JPanel panelGameMovesPerSecond = new JPanel();
			panelGameMovesPerSecond.setOpaque(false);

			frameGameMovesPerSecond.add(panelGameMovesPerSecond);

			panelGameMovesPerSecond.add(new JLabel("Number of moves per second:"));

			Integer[] secondOptions = {1,5,10,15,25,50,100};	
			final JComboBox<Integer> comboBoxSeconds = new JComboBox<>(secondOptions);

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

		} else if (actionEvent.getSource().equals(gameAutofill)){
			//TODO Generates cells randomly
			System.out.println("autofill");

		} else if (actionEvent.getSource().equals(gameExit)){
			// Exit the game
			System.exit(0);
		}

		// Sub-menu Statistics
		else if (actionEvent.getSource().equals(statisticsView)){
			//TODO Statistics
			System.out.println("Statistcs");
		}

		// Sub-menu Rule
		else {
			for(int index = 0; index < ruleList.size(); index++){
				ruleList.get(index).setEnabled(true);

				if (actionEvent.getSource().equals(ruleList.get(index))){	
					engine.setStrategy((Strategy)context.getBean(ruleList.get(index).getName()));

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

		public void removeCell(int x, int y) {
			cell.remove(new Cell(x,y));
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
			survivingCells = engine.getStrategy().survivors(gameBoard, survivingCells);

			// Adding the survivingCells to the board
			resetBoard();
			cell.addAll(survivingCells);
			repaint();

			try {
				Thread.sleep(1000/movesPerSecond);
				run();
			} catch (InterruptedException ex) {}

		}
	}
}
