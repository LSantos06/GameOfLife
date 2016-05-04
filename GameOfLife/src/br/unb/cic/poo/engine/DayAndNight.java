package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do DayAndNight. 
 * 
 * B3678/S34678
 * 
 * @author LSantos06
 */
public class DayAndNight implements Strategy{
	@Override
	public String getName() {
		return "DayAndNight (B3678/S34678)";
	}
	
	@Override
	public String getBeanName(){
		return "dayandnight";
	}
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 || 
				engine.numberOfNeighborhoodAliveCells(i, j) == 4 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 8; 
	}
}
