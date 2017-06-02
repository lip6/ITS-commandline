package fr.lip6.move.gal.itscl.application;

public class ResultP {

	public final static int OK = 0;
	public final static int KO = 1;
	public final static int UNKNOWN = 2;
	private final int res;
	
	public ResultP(int res){
		this.res=res;
	}
	
	public String toString(){
		return "DONE "+String.valueOf(res);
	}
}
