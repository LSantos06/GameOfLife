package br.unb.cic.poo.tutorial;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Triangle implements ApplicationContextAware, BeanNameAware{

//	private String type;
//	private int height;
//	
//	
//	public Triangle(String type){
//		this.type = type;
//	}
//	
//	public Triangle(int height){
//		this.height = height;
//	}
//	
//	public Triangle(String type, int height){
//		this.type = type;
//		this.height = height;
//	}
//	
//	
//	public String getType() {
//		return type;
//	}
//
//	public int getHeight() {
//		return height;
//	}
	
//	public void setType(String type) {
//		this.type = type;
//	}

	
//	public void draw() {
//		System.out.println(getType() + " triangle drawn of heigh " + getHeight());
//	}
	
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	private ApplicationContext context = null;

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	public void draw(){
		System.out.println("(" + getPointA().getX() + "," + getPointA().getY() + ")");
		System.out.println("(" + getPointB().getX() + "," + getPointB().getY() + ")");
		System.out.println("(" + getPointC().getX() + "," + getPointC().getY() + ")");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@Override
	public void setBeanName(String beanName) {
		System.out.println(beanName);
	}
}
