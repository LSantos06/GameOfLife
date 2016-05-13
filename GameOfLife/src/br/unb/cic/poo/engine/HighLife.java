package br.unb.cic.poo.engine;

import java.util.ArrayList;

import br.unb.cic.poo.game.Cell;
import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do HighLife. 
 * 
 * B36/S23
 * 
 * @author LSantos06
 */
public class HighLife implements Strategy{
	@Override
	public String getName() {
		return "HighLife (B36/S23)"; 
	}
	
	@Override
	public String getBeanName(){
		return "highlife";
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
                    // Cell is alive, Can the cell survives? S(23)
                    if ((surrounding == 2) || (surrounding == 3)) {
                        survivingCells.add(new Cell(i-1,j-1));
                    } else {
                    	engine.getStatistics().recordKill();
                    }
                } else {
                    // Cell is dead, will the cell be given birth? B(36)
                    if ((surrounding == 3) || (surrounding == 6)) {
                        survivingCells.add(new Cell(i-1,j-1));
                        engine.getStatistics().recordRevive();
                    }
                }
            }
        }
		return survivingCells;
	}
}