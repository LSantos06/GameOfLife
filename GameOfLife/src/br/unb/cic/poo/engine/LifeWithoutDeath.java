package br.unb.cic.poo.engine;

import br.unb.cic.poo.model.GameEngine;

/**
 * Implementacao de uma estrategia de derivacao 
 * baseada nas regras do LifeWithoutDeath. 
 * 
 * B3/S012345678
 * 
 * @author LSantos06
 */
public class LifeWithoutDeath extends Conway{
	@Override
	public String getName() {
		return "LifeWithoutDeath (B3/S012345678)";
	}
	
	@Override
	public String getBeanName(){
		return "lifewithoutdeath";
	}
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return true;
	}
}
