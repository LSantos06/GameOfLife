package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

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
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 1 || 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 5 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 1 || 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 5 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7;
	}
}
