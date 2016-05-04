package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do Diamoeba. 
 * 
 * B35678/S5678
 * 
 * @author LSantos06
 */
public class Diamoeba implements Strategy{
	@Override
	public String getName() {
		return "Diamoeba (B35678/S5678)";
	}
	
	@Override
	public String getBeanName(){
		return "diamoeba";
	}
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 5 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 5 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8; 
	}
}
