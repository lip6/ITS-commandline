package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.itscl.interpreter.IInterpreteObservable;
import fr.lip6.move.gal.itscl.modele.ISolverSeq;
import fr.lip6.move.gal.itstools.CommandLine;

public abstract class ItsSolver implements ISolverSeq {

	private final CommandLine cl;
	protected final ProblemSS p;
	protected IInterpreteObservable inRunner;
	
	public ItsSolver(ProblemSS p, CommandLine cl) {
		this.cl = cl;
		this.p = p;
	}
	
	public CommandLine getCmd() {
		return cl;
	}

	public ProblemSS getProblem() {
		return p;
	}
	
	public void configureInterpreter(IInterpreteObservable interp){
		this.inRunner=interp;
	}

}
