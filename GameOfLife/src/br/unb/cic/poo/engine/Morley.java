package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do Morley. 
 * 
 * B368/S245
 * 
 * @author LSantos06
 */
public class Morley implements Strategy{
	@Override
	public String getName() {
		return "Morley (B368/S245)";
	}
	
	@Override
	public String getBeanName(){
		return "morley";
	}
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 2 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 4 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 5;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8; 
	}
}
