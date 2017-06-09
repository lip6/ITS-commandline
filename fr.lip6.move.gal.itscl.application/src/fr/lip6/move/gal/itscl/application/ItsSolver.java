package fr.lip6.move.gal.itscl.application;



import fr.lip6.move.gal.itstools.CommandLine;

public class  ItsSolver {
	
	private final CommandLine cl;
	protected final Problem p;
	
	public ItsSolver(Problem p,CommandLine cl){
		this.cl=cl;
		this.p=p;
	}

	public CommandLine getCmd() {
		return cl;
	}
	
	public Problem getProblem(){
		return p;
	}
	
	


}
