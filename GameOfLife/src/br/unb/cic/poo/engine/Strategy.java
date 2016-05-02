package br.unb.cic.poo.engine;

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
	
	/**
	 * Verifica se uma celula (na posica (i, j)) deve 
	 * ser mantida viva. 
	 * 
	 * @param i posicao da celula na i-esima linha
	 * @param j posicao da celula na j-esima coluna 
	 * 
	 * @param engine referencia para a Game Engine
	 * 
	 * @return <i>true</i> caso a celula deva ser mantida viva. 
	 * 		   <i>false</i> caso contrario. 
	 */
	public boolean shouldKeepAlive(int i, int j, GameEngine engine);
	
	/**
	 * Verifica se uma celula (na posica (i, j)) deve 
	 * se tornar uma celula viva. 
	 * 
	 * @param i posicao da celula na i-esima linha
	 * @param j posicao da celula na j-esima coluna 
	 * 
	 * @param engine referencia para a Game Engine
	 * 
	 * @return <i>true</i> caso a celula deva se tornar viva. 
	 *         <i>false</i> caso contrario. 
	 */
	public boolean shouldRevive(int i, int j, GameEngine engine);

}
