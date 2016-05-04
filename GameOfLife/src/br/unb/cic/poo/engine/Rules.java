package br.unb.cic.poo.engine;

import java.util.ArrayList;

public class Rules {
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>(0);

	public ArrayList<Strategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(ArrayList<Strategy> strategies) {
		this.strategies = strategies;
	}

}
