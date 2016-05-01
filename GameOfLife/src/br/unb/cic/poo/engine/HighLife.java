package br.unb.cic.poo.engine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do HighLife. 
 * 
 * B36/S23
 * 
 * @author rbonifacio
 */
public class HighLife extends Conway {
	@Override
	public String getName() {
		return "HighLife"; 
	}
	
	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				(engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				 engine.numberOfNeighborhoodAliveCells(i, j) == 6);
	}
}
