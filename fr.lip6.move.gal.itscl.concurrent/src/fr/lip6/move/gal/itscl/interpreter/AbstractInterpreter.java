package fr.lip6.move.gal.itscl.interpreter;

import java.util.concurrent.Semaphore;

public abstract class AbstractInterpreter implements Runnable {

	private Semaphore hasComplete = new Semaphore(0);

	/**
	 * Block until interpreter of the specific runner complete
	 */
	public void acquireResult() {
		try {
			hasComplete.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * notify its runner that I finish the last interpretation
	 */
	public void releaseResult() {
		hasComplete.release();
	}

}
