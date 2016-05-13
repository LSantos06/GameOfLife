package br.unb.cic.poo.rules;

import java.util.ArrayList;

import br.unb.cic.poo.engine.GameEngine;
import br.unb.cic.poo.game.Cell;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do Replicator. 
 * 
 * B1357/S1357
 * 
 * @author LSantos06
 */
public class Replicator implements Strategy{
	@Override
	public String getName() {
		return "Replicator (B1357/S1357)";
	}
	
	@Override
	public String getBeanName(){
		return "replicator";
	}

	@Override
	public ArrayList<Cell> survivors(boolean[][] gameBoard, ArrayList<Cell> survivingCells, GameEngine engine) {
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
                    // Cell is alive, Can the cell survives? S(1357)
                    if ((surrounding == 1) || (surrounding == 3) || (surrounding == 5) ||
                        (surrounding == 7)) {
                        survivingCells.add(new Cell(i-1,j-1));
                    } else {
                    	engine.getStatistics().recordKill();
                    }
                } else {
                    // Cell is dead, will the cell be given birth? B(1357)
                    if ((surrounding == 1) || (surrounding == 3) || (surrounding == 5) ||
                        (surrounding == 7)) {
                        survivingCells.add(new Cell(i-1,j-1));
                        engine.getStatistics().recordRevive();
                    }
                }
            }
        }
		return survivingCells;
	}
}