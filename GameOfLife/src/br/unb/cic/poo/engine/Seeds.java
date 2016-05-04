package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do LifeWithoutDeath. 
 * 
 * B2/S
 * 
 * @author rbonifacio
 */
public class Seeds implements Strategy{
	@Override
	public String getName() {
		return "Seeds (B2/S)";
	}

	@Override
	public String getBeanName(){
		return "seeds";
	}
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 0;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 2;
	}
}
