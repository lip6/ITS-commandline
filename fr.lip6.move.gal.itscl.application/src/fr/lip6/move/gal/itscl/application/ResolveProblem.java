package fr.lip6.move.gal.itscl.application;

public class ResolveProblem {

	public final static int OK = 0;
	public final static int KO = 1;
	public final static int UNKNOWN = 2;
	private final int res;
	
	public ResolveProblem(int res){
		this.res=res;
	}
	
	public String toString(){
		return "DONE "+String.valueOf(res);
	}
}
