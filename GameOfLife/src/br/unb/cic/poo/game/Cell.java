package br.unb.cic.poo.game;

public class Cell{
    private boolean alive;
    private int x;
    private int y;
    
    public Cell(int x, int y) {
    	alive = false;
    	this.x = x;
    	this.y = y;
    }

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
