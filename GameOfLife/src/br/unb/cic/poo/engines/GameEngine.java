package br.unb.cic.poo.engines;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class GameEngine {

	public static void main(String[] args) {
		//Conway conway = new Conway();
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		Conway conway = (Conway)context.getBean("conway");
		conway.game();
	}

}
