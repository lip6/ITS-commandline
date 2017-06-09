package fr.lip6.move.gal.itscl.application;

import java.util.concurrent.Callable;

public interface ISolver extends Callable<Integer> {

	public void currentState();
	public int isComplete();
	
}
