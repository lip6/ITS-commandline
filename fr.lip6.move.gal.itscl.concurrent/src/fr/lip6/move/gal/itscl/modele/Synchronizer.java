package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Semaphore;

public class Synchronizer {

	private Semaphore interpWriteOut = new Semaphore(0);

	public void notifySolver() {
		interpWriteOut.release();
	}
	
	/**
	 * {@link SolverObservable} waits till all interpreters end correctly
	 */
	public void waitInterpreters() {

		try {
			interpWriteOut.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
