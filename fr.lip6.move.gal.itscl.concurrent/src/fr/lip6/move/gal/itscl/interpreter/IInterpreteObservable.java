package fr.lip6.move.gal.itscl.interpreter;

import java.util.concurrent.Callable;


public interface IInterpreteObservable extends Callable<Boolean> {
	public void addThInterprete(Thread o);

}
