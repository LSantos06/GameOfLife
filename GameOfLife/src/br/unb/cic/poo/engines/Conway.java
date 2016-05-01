package br.unb.cic.poo.engines;

public class Conway {
	
//	private String type;
//	private int number;
//	
//	
//	public Conway(String type){
//		this.type = type;
//	}
//	
//	public Conway(int number){
//		this.number = number;
//	}
//	
//	public Conway(String type, int number){
//		this.type = type;
//		this.number = number;
//	}
//	
//	
//	public String getType() {
//		return type;
//	}
//
//	public int getNumber() {
//		return number;
//	}
//	
////	public void setType(String type) {
////		this.type = type;
////	}
//
//	
//	public void game() {
//		System.out.println(getType() + ": Game Conway " + getNumber());
//	}
	
	
	private HighLife A;
	private HighLife B;
	private HighLife C;
	
	
	public HighLife getA() {
		return A;
	}

	public void setA(HighLife a) {
		A = a;
	}

	public HighLife getB() {
		return B;
	}

	public void setB(HighLife b) {
		B = b;
	}

	public HighLife getC() {
		return C;
	}

	public void setC(HighLife c) {
		C = c;
	}
	
	
	public void game(){
		System.out.println("A, alives = " + getA().getAlive() + ", deads = " + getA().getDead());
		System.out.println("A, alives = " + getB().getAlive() + ", deads = " + getB().getDead());
		System.out.println("A, alives = " + getC().getAlive() + ", deads = " + getC().getDead());
	}
}
