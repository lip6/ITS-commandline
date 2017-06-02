package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.itstools.CommandLine;

public class  ItsSolver implements ISolver  {
	
	private final CommandLine cl;
	private final Problem p;
	private ResultP result=new ResultP(ResultP.UNKNOWN);

	
	
	public ItsSolver(Problem p,CommandLine cl){
		this.cl=cl;
		this.p=p;
	}

	public void solve(){
		
	}

	public CommandLine getCmd() {
		return cl;
	}

	public Problem getP() {
		return p;
	}

	public ResultP getResult() {
		return result;
	}
	
	public void setResult(ResultP res){
		result=res;
	}

}
