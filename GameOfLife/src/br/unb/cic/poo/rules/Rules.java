package br.unb.cic.poo.rules;

import java.util.ArrayList;

/**
 * Classe que implementa a injeção de dependência no atributo strategies
 *
 * @author LSantos06
 */
public class Rules {
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>(0);

	public ArrayList<Strategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(ArrayList<Strategy> strategies) {
		this.strategies = strategies;
	}

}
