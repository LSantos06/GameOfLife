package br.unb.cic.poo.controller;

import br.unb.cic.poo.game.Statistics;
import br.unb.cic.poo.model.GameEngine;

/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {
	private GameEngine engine;
	private Statistics statistics;

	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}	
}
