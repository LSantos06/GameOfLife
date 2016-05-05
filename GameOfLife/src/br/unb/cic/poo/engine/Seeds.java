package br.unb.cic.poo.engine;

import java.util.ArrayList;

import br.unb.cic.poo.game.Cell;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do LifeWithoutDeath. 
 * 
 * B2/S
 * 
 * @author LSantos06
 */
public class Seeds implements Strategy{
	@Override
	public String getName() {
		return "Seeds (B2/S)";
	}

	@Override
	public String getBeanName(){
		return "seeds";
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
                    // Cell is alive, Can the cell survives? S()
                } else {
                    // Cell is dead, will the cell be given birth? B(2)
                    if (surrounding == 2) {
                        survivingCells.add(new Cell(i-1,j-1));
                    }
                }
            }
        }
		return survivingCells;
	}
}
