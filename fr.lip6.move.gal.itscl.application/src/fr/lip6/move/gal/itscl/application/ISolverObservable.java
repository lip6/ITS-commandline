package fr.lip6.move.gal.itscl.application;


import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import fr.lip6.move.gal.application.Ender;

public interface ISolverObservable extends Callable<Boolean> ,Ender {
	public void attach(ISolver obs) ;
	public void detach(ISolver obs) ;
	public void setTimeout(int timeout,TimeUnit u);
	
}
