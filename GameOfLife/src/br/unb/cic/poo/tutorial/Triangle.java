package br.unb.cic.poo.tutorial;

import java.util.List;

public class Triangle {

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
	
	
	private List<Point> points;
	
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public void draw(){
		
		for (Point point: points) {
			System.out.println("(" + point.getX() + "," + point.getY() + ")");
		}
	}
}
