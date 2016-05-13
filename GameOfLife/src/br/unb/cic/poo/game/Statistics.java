package br.unb.cic.poo.game;

public class Statistics {
	private int revivedCells;
	private int killedCells;
	
	public Statistics() {
		revivedCells = 0;
		killedCells = 0;
	}

	public int getRevivedCells() {
		return revivedCells;
	}

	public void setRevivedCells(int revivedCells) {
		this.revivedCells = revivedCells;
	}

	public int getKilledCells() {
		return killedCells;
	}
	
	public void setKilledCells(int killedCells) {
		this.killedCells = killedCells;
	}

	public void recordRevive() {
		this.revivedCells++;
	}

	public void recordKill() {
		this.killedCells++;
	}
}
