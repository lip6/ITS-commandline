package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.itstools.CommandLine;

public class  ItsSolver extends SolverObservable implements ISolver  {
	
	private final CommandLine cl;
	protected final Problem p;
	protected final SolverObservable obs;
	
	public ItsSolver(Problem p,CommandLine cl, SolverObservable so){
		this.cl=cl;
		this.p=p;
		this.obs=so;
	}

	public Thread solve(){
		return new Thread();
	}

	public CommandLine getCmd() {
		return cl;
	}
	
	public Problem getProblem(){
		return p;
	}

	public SolverObservable getObs() {
		return obs;
	}


}
