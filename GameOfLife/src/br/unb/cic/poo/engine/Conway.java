package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

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
 * @author rbonifacio
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
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 2 || 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 3; 
	}
}
