package br.unb.cic.poo.engine;

import java.util.ArrayList;

import br.unb.cic.poo.game.Cell;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do Conway. 
 * 
 * https://en.wikipedia.org/wiki/Life-like_cellular_automaton
 * 
 * B = Born, shouldRevive
 * S = Survives, shouldKeepAlive
 * 
 * B3/S23
 * 
 * @author LSantos06
 */
public class Conway implements Strategy{
	@Override
	public String getName() {
		return "Conway (B3/S23)";
	}
	
	@Override
	public String getBeanName(){
		return "conway";
	}

	@Override
	public ArrayList<Cell> survivors(boolean[][] gameBoard, ArrayList<Cell> survivingCells) {
        
        // Iterate through the array, follow game of life rules
        for (int i=1; i<gameBoard.length-1; i++) {
            for (int j=1; j<gameBoard[0].length-1; j++) {
            	
            	// Counting the neighborhood alive cells
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
                    // Cell is alive, Can the cell survives? S(23)
                    if ((surrounding == 2) || (surrounding == 3)) {
                        survivingCells.add(new Cell(i-1,j-1));
                    } 
                } else {
                    // Cell is dead, will the cell be given birth? B(3)
                    if (surrounding == 3) {
                        survivingCells.add(new Cell(i-1,j-1));
                    }
                }
            }
        }
		return survivingCells;
	}
}
