package fr.lip6.move.gal.itscl.application;

public class ResultProblem {

	final static short OK = 0;
	final static short KO = 1;
	final static short UNKNOWN = 2;
	private final short res;
	
	ResultProblem(short res){
		this.res=res;
	}
	
	public String toString(){
		return "DONE "+String.valueOf(res);
	}
}
