package br.unb.cic.poo.engine;

import java.util.ArrayList;

import br.unb.cic.poo.game.Cell;
import br.unb.cic.poo.model.GameEngine;

/**
 * Interface com a declaracao dos metodos  que apoiam o 
 * calculo de uma proxima derivacao no GameOfLife. 
 *
 * @author rbonifacio
 */
public interface Strategy {
	/** Retorna o nome da regra de derivacao */	
	public String getName();
	
	/** Retorna o nome do bean associado a regra de derivacao */
	public String getBeanName();
	
	/** Retorna a lista de celulas sobreviventes */
	public ArrayList<Cell> survivors(boolean[][] gameBoard, ArrayList<Cell> survivingCells, GameEngine engine);
}
