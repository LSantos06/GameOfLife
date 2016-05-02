package br.unb.cic.poo.view;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.*;

public class GameGUI extends JFrame implements ActionListener{
    //TODO
	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
    private static final int BLOCK_SIZE = 10;
	
	private JMenuBar menu;
    private JMenu game, rule, statistics;
    private JMenuItem gamePlay, gameStop, gameReset, gameMovesPerSecond, gameAutofill, gameExit;
    private JMenuItem ruleAnneal, ruleConway, ruleDayAndNight, ruleDiamoeba, ruleHighLife, ruleLifeWithoutDeath, ruleMorley, ruleReplicator, ruleSeeds;
    private int movesPerSecond = 3;
    private GameBoard gameBoard;
    private Thread gameOfLife;
    
    public static void main(String[] args) {
        // Setup the swing specifics
        JFrame game = new GameGUI();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setTitle("Conway's Game of Life");
        game.setSize(DEFAULT_WINDOW_SIZE);
        game.setMinimumSize(MINIMUM_WINDOW_SIZE);
        game.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - game.getWidth())/2, 
                (Toolkit.getDefaultToolkit().getScreenSize().height - game.getHeight())/2);
        game.setVisible(true);
    }
    
    public GameGUI(){
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
        ruleAnneal = new JMenuItem("Anneal B4678/S35678");
        ruleAnneal.addActionListener(this);

        ruleConway = new JMenuItem("Conway B3/S23");
        ruleConway.addActionListener(this);

        ruleDayAndNight = new JMenuItem("DayAndNight B3678/S34678");
        ruleDayAndNight.addActionListener(this);
        
        ruleDiamoeba = new JMenuItem("Diamoeba B35678/S5678");
        ruleDiamoeba.addActionListener(this);

        ruleHighLife = new JMenuItem("High Life B36/S23");
        ruleHighLife.addActionListener(this);
        
        ruleLifeWithoutDeath = new JMenuItem("Life Without Death B3/S012345678");
        ruleLifeWithoutDeath.addActionListener(this);
        
        ruleMorley = new JMenuItem("Morley B368/S245");
        ruleMorley.addActionListener(this);
        
        ruleReplicator = new JMenuItem("Replicator B1357/S1357");
        ruleReplicator.addActionListener(this);
        
        ruleSeeds = new JMenuItem("Seeds B2/S");
        ruleSeeds.addActionListener(this);
        
        rule.add(ruleAnneal);
        rule.add(ruleConway);
        rule.add(ruleDayAndNight);
        rule.add(ruleDiamoeba);
        rule.add(ruleHighLife);
        rule.add(ruleLifeWithoutDeath);
        rule.add(ruleMorley);
        rule.add(ruleReplicator);
        rule.add(ruleSeeds);
        
        // Column Statistics
        statistics = new JMenu("Statistics");
        statistics.addActionListener(this);
        
        // Setup game board
        gameBoard = new GameBoard();
        add(gameBoard);    	
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
			//TODO Play the game
		} else if (actionEvent.getSource().equals(gameStop)){
			//TODO Stop the game
		} else if (actionEvent.getSource().equals(gameReset)){
			//TODO Reset the game
		} else if (actionEvent.getSource().equals(gameMovesPerSecond)){
			//TODO Defines the moves per second
		} else if (actionEvent.getSource().equals(gameAutofill)){
			//TODO Generates cells randomly
		} else if (actionEvent.getSource().equals(gameExit)){
			//TODO Exit the game
		}
		
		// Sub-menu Rule
		else if (actionEvent.getSource().equals(ruleAnneal)){
			//TODO Anneal
		} else if (actionEvent.getSource().equals(ruleConway)){
			//TODO Conway
		} else if (actionEvent.getSource().equals(ruleDayAndNight)){
			//TODO DayAndNight
		} else if (actionEvent.getSource().equals(ruleDiamoeba)){
			//TODO Diamoeba
		} else if (actionEvent.getSource().equals(ruleHighLife)){
			//TODO HighLife
		} else if (actionEvent.getSource().equals(ruleLifeWithoutDeath)){
			//TODO LifeWithoutDeath
		} else if (actionEvent.getSource().equals(ruleMorley)){
			//TODO Morley
		} else if (actionEvent.getSource().equals(ruleReplicator)){
			//TODO Replicator
		} else if (actionEvent.getSource().equals(ruleSeeds)){
			//TODO Seeds
		}
		
		// Sub-menu Statistics
		else if (actionEvent.getSource().equals(statistics)){
			//TODO Statistics
            JOptionPane.showMessageDialog(null, "Statistics");
		}
	}
    
	  private class GameBoard extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, Runnable {
	        private Dimension gameBoardSize = null;
	        private ArrayList<Point> point = new ArrayList<Point>(0);
	        
	        public GameBoard() {
	            // Add resizing listener
	            addComponentListener(this);
	            addMouseListener(this);
	            addMouseMotionListener(this);
	        }
	        
	        private void updateArraySize() {
	            ArrayList<Point> removeList = new ArrayList<Point>(0);
	            for (Point current : point) {
	                if ((current.x > gameBoardSize.width-1) || (current.y > gameBoardSize.height-1)) {
	                    removeList.add(current);
	                }
	            }
	            point.removeAll(removeList);
	            repaint();
	        }
	        
	        public void addPoint(int x, int y) {
	            if (!point.contains(new Point(x,y))) {
	                point.add(new Point(x,y));
	            } 
	            repaint();
	        }
	        
	        public void addPoint(MouseEvent me) {
	            int x = me.getPoint().x/BLOCK_SIZE-1;
	            int y = me.getPoint().y/BLOCK_SIZE-1;
	            if ((x >= 0) && (x < gameBoardSize.width) && (y >= 0) && (y < gameBoardSize.height)) {
	                addPoint(x,y);
	            }
	        }
	        
	        public void removePoint(int x, int y) {
	            point.remove(new Point(x,y));
	        }
	        
	        public void resetBoard() {
	            point.clear();
	        }
	        
	        public void randomlyFillBoard(int percent) {
	            for (int i=0; i<gameBoardSize.width; i++) {
	                for (int j=0; j<gameBoardSize.height; j++) {
	                    if (Math.random()*100 < percent) {
	                        addPoint(i,j);
	                    }
	                }
	            }
	        }
	        
	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            try {
	                for (Point newPoint : point) {
	                    // Draw new point
	                    g.setColor(Color.darkGray);
	                    g.fillRect(BLOCK_SIZE + (BLOCK_SIZE*newPoint.x), BLOCK_SIZE + (BLOCK_SIZE*newPoint.y), BLOCK_SIZE, BLOCK_SIZE);
	                }
	            } catch (ConcurrentModificationException cme) {}
	            // Setup grid
	            g.setColor(Color.BLACK);
	            for (int i=0; i<=gameBoardSize.width; i++) {
	                g.drawLine(((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE, (i*BLOCK_SIZE)+BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE*gameBoardSize.height));
	            }
	            for (int i=0; i<=gameBoardSize.height; i++) {
	                g.drawLine(BLOCK_SIZE, ((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE*(gameBoardSize.width+1), ((i*BLOCK_SIZE)+BLOCK_SIZE));
	            }
	        }

	        @Override
	        public void componentResized(ComponentEvent e) {
	            // Setup the game board size with proper boundries
	        	gameBoardSize = new Dimension(getWidth()/BLOCK_SIZE-2, getHeight()/BLOCK_SIZE-2);
	            updateArraySize();
	        }
	        @Override
	        public void componentMoved(ComponentEvent e) {}
	        @Override
	        public void componentShown(ComponentEvent e) {}
	        @Override
	        public void componentHidden(ComponentEvent e) {}
	        @Override
	        public void mouseClicked(MouseEvent e) {}
	        @Override
	        public void mousePressed(MouseEvent e) {}
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            // Mouse was released (user clicked)
	            addPoint(e);
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {}

	        @Override
	        public void mouseExited(MouseEvent e) {}

	        @Override
	        public void mouseDragged(MouseEvent e) {
	            // Mouse is being dragged, user wants multiple selections
	            addPoint(e);
	        }
	        @Override
	        public void mouseMoved(MouseEvent e) {}

	        @Override
	        public void run() {
	            boolean[][] gameBoard = new boolean[gameBoardSize.width+2][gameBoardSize.height+2];
	            for (Point current : point) {
	                gameBoard[current.x+1][current.y+1] = true;
	            }
	            ArrayList<Point> survivingCells = new ArrayList<Point>(0);
	            // Iterate through the array, follow game of life rules
	            for (int i=1; i<gameBoard.length-1; i++) {
	                for (int j=1; j<gameBoard[0].length-1; j++) {
	                    int surrounding = 0;
	                    if (gameBoard[i-1][j-1]) { surrounding++; }
	                    if (gameBoard[i-1][j])   { surrounding++; }
	                    if (gameBoard[i-1][j+1]) { surrounding++; }
	                    if (gameBoard[i][j-1])   { surrounding++; }
	                    if (gameBoard[i][j+1])   { surrounding++; }
	                    if (gameBoard[i+1][j-1]) { surrounding++; }
	                    if (gameBoard[i+1][j])   { surrounding++; }
	                    if (gameBoard[i+1][j+1]) { surrounding++; }
	                    if (gameBoard[i][j]) {
	                        // Cell is alive, Can the cell live? (2-3)
	                        if ((surrounding == 2) || (surrounding == 3)) {
	                            survivingCells.add(new Point(i-1,j-1));
	                        } 
	                    } else {
	                        // Cell is dead, will the cell be given birth? (3)
		                        if (surrounding == 3) {
		                            survivingCells.add(new Point(i-1,j-1));
		                        }
		                    }
		                }
		            }
		            resetBoard();
		            point.addAll(survivingCells);
		            repaint();
		            try {
		                Thread.sleep(1000/movesPerSecond);
		                run();
		            } catch (InterruptedException ex) {}
		        }
		    }

    
}
