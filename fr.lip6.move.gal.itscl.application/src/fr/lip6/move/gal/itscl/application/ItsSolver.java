package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.application.Ender;
import fr.lip6.move.gal.itstools.CommandLine;

public class  ItsSolver implements ISolver  {
	
	private final CommandLine cl;
	protected final Problem p;
	
	public ItsSolver(Problem p,CommandLine cl){
		this.cl=cl;
		this.p=p;
	}

	public void solve(Ender obs){
	}

	public CommandLine getCmd() {
		return cl;
	}
	
	public Problem getProblem(){
		return p;
	}




}
