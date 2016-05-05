package br.unb.cic.poo.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import br.unb.cic.poo.engine.Strategy;
import br.unb.cic.poo.game.Cell;
import br.unb.cic.poo.game.Statistics;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * @author rbonifacio
 */
public class GameEngine {
	private int height;
	private int width;
	private Cell[][] cells;
	private Statistics statistics;
	private Strategy strategy;

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimensao horizontal do ambiente
	 */
	public GameEngine(int height, int width, Statistics statistics) {
		this.height = height;
		this.width = width;
		
		// Criacao do tabuleiro
		cells = new Cell[height][width];
		
		// Cria uma celula para cada posicao do tabuleiro
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell(i,j);
			}
		}
		
		this.statistics = statistics;
	}
	
	/* Metodos de acesso a estrategia de jogo */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	/*
	 * Injecao de dependencia
	 * 
	 *
	public boolean shouldKeepAlive(int i, int j, Strategy strategy){
		return this.strategy.shouldKeepAlive(i, j, this);
	}
	
	public boolean shouldRevive(int i, int j, Strategy strategy){
		return this.strategy.shouldRevive(i, j, this);
	}
	*/
	
	/*
	 * Calcula uma nova geracao do ambiente. Essa implementacao delega para 
	 * a estrategia de derivacao a logica necessaria para identificar 
	 * se uma celula deve se tornar viva ou ser mantida viva na proxima 
	 * geracao. 
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
//				if (strategy.shouldRevive(i, j, this)) {
//					mustRevive.add(cells[i][j]);
//				} 
//				else if ((!strategy.shouldKeepAlive(i, j, this)) && cells[i][j].isAlive()) {
//					mustKill.add(cells[i][j]);
//				}
			}
		}
		
		updateStatistics(mustRevive, mustKill);
	}

	/*
	 * Metodo auxiliar que atualiza as estatisticas das celulas 
	 * que foram mortas ou se tornaram vivas entre duas geracoes. 
	 */
	private void updateStatistics(List<Cell> mustRevive, List<Cell> mustKill) {
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	public int numberOfNeighborhoodAliveCells(int i, int j) throws InvalidParameterException {
		int alive = 0;
		
		if(validPosition(i, j)) {
			for (int a = i - 1; a <= i + 1; a++) {
				for (int b = j - 1; b <= j + 1; b++) {
					if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
						alive++;
					}
				}
			}
			return alive;
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
