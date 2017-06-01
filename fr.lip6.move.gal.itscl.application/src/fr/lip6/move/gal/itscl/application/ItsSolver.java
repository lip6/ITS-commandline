package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.itstools.CommandLine;

public class  ItsSolver implements ISolver  {
	
	private final CommandLine cmd;
	
	public ItsSolver(CommandLine cmd){
		this.cmd=cmd;
	}

	synchronized public ResultP solve(Problem p){
		
		return new ResultP(ResultP.UNKNOWN);

	}

	public CommandLine getCmd() {
		return cmd;
	}
	
}
