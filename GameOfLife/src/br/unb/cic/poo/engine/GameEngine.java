package br.unb.cic.poo.engine;

import br.unb.cic.poo.game.Statistics;
import br.unb.cic.poo.rules.Strategy;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * @author rbonifacio
 */
public class GameEngine {
	private Statistics statistics;
	private Strategy strategy;

	/**
	 * Construtor da classe Environment.
	 */
	public GameEngine(Statistics statistics) {		
		this.statistics = statistics;
	}
	
	/* Metodos de acesso a estrategia de jogo */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
}
