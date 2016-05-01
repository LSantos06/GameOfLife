package br.unb.cic.poo.game;

public class Cell {
    private boolean alive;
    
    public Cell() {
    	alive = false;
    }

    public Cell(boolean status) {
    	alive = status; 
    }
    
    public boolean isAlive() {
    	return alive;
    }

    public void kill() {
    	this.alive = false;
    }
	
    public void revive() {
    	this.alive = true;
    }
}
