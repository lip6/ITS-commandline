package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterpreteObservable implements IInterpreteObservable {
	private ExecutorService execInterp = Executors.newCachedThreadPool();
	private Synchronizer sync;

	public InterpreteObservable(Synchronizer sync) {
		this.sync = sync;
	}

	public void launchInterprete(IListener o) {
		execInterp.submit(o);
	}

	/**
	 * wait a least five minutes till all interpreters have terminate
	 */
	public void killAll() {
		execInterp.shutdown();
		while (!execInterp.isShutdown()) {
			try {
				execInterp.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e){
				System.out.println("Timeout waiting for interpreters to finish ");
				e.printStackTrace();
			}
		}
		sync.notifySolver();
	}

	public void run() {
		try {
			if (execInterp.awaitTermination(5, TimeUnit.MINUTES))
				System.out.println("Interpreters are done !");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		killAll();
	}
}
