package br.unb.cic.poo.engine;

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
		return "LiveFreeOrDie";
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
