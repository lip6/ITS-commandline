package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.itstools.CommandLine;

public class Problem {
	
	private final CommandLine cmd;
	
	Problem(CommandLine cmd){
		this.cmd=cmd;
	}

	public CommandLine getCmd() {
		return cmd;
	}
	
}
