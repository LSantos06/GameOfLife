package br.unb.cic.poo.tutorial;

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
	
	
	private Point A;
	private Point B;
	private Point C;
	
	
	public Point getA() {
		return A;
	}

	public void setA(Point a) {
		A = a;
	}

	public Point getB() {
		return B;
	}

	public void setB(Point b) {
		B = b;
	}

	public Point getC() {
		return C;
	}

	public void setC(Point c) {
		C = c;
	}
	
	
	public void draw(){
		System.out.println("A = (" + getA().getX() + "," + getA().getY() + ")");
		System.out.println("B = (" + getB().getX() + "," + getB().getY() + ")");
		System.out.println("C = (" + getC().getX() + "," + getC().getY() + ")");
	}
}
