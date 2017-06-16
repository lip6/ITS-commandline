package fr.lip6.move.gal.itscl.application;

public class ResultP implements IResultP {

	public final static int OK = 0;
	public final static int KO = 1;
	public final static int UNKNOWN = 2;
	private final int res;

	public ResultP(int res) {
		this.res = res;
	}

	public String toString() {
		if (res == OK)
			return "DONE " + String.valueOf(res);
		else
			return "Error :" + String.valueOf(res);
	}

	public boolean isOK() {
		return (res == OK) ? true : false;
	}
	
	public boolean isUnknown(){
		return res==UNKNOWN? true:false;
	}
	
	
}
