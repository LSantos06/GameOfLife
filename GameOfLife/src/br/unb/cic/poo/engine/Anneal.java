package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do Anneal. 
 * 
 * B4678/S35678
 * 
 * @author LSantos06
 */
public class Anneal implements Strategy{
	@Override
	public String getName() {
		return "Anneal (B4678/S35678)";
	}
	
	@Override
	public String getBeanName(){
		return "anneal";
	}
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 5 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 4 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8; 
	}
}
