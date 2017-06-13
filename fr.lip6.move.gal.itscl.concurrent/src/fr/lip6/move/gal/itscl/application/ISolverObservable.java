package fr.lip6.move.gal.itscl.application;


import java.util.concurrent.Callable;

public interface ISolverObservable extends Callable<Boolean>  {
	public void attach(ISolver obs) ;
	public void detach(ISolver obs) ;
	public void killAll();
	
}
