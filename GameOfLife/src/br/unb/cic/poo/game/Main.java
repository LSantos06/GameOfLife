package br.unb.cic.poo.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.unb.cic.poo.controller.GameController;
import br.unb.cic.poo.engine.Conway;
import br.unb.cic.poo.model.GameEngine;
import br.unb.cic.poo.tutorial.Triangle;
import br.unb.cic.poo.view.GameView;

/**
 * Classe que define o metodo principal do programa; ou 
 * seja, o metodo que vai ser executado pela JVM para inicializar 
 * o programa. 
 * 
 * @author rbonifacio
 */
public class Main {

	public static void main(String[] args) {
		GameController controller = new GameController();
		
		Statistics statistics = new Statistics();

		GameEngine engine = new GameEngine(10, 10, statistics);	
		
		/*
		 * Utilizando a injecao de dependencia
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("game.xml");
		
		/*
		 * Nessa implementacao, a estrategia do Conway eh 
		 * a padrao.
		 */
		engine.setStrategy((Conway)context.getBean("conway"));
		
		GameView board = new GameView(controller, engine);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();

	}

}
