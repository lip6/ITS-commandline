package fr.lip6.move.gal.itscl.modele;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterpreteObservable implements IInterpreteObservable {
	private Set<IListener> interp;
	private ExecutorService execInterp = Executors.newCachedThreadPool();

	public void attach(IListener o) {
		interp.add(o);
	}

	public void detach(IListener o) {
		interp.remove(o);
	}
	
	public void killAll() {
		execInterp.shutdown();
		try {
			if (execInterp.awaitTermination(5, TimeUnit.MINUTES))
				System.out.println("Interpreters are done !");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public Boolean call() throws Exception {

		try {
			execInterp.invokeAll(interp);
			this.wait();
			killAll();
			return true;
		} catch (InterruptedException e1) {
			System.out.println("Interpreter don't terminate well");
			return false;
		}

	}
}
