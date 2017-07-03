package fr.lip6.move.gal.itscl.modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class SolverObservable implements ISolverObservable {

	private Set<ISolverSeq> obs = new HashSet<>();
	private List<Future<Integer>> fsolvers = new ArrayList<Future<Integer>>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	private Semaphore interpreteComplete = new Semaphore(0);
	private Semaphore stopInterpretation = new Semaphore(0);

	public void attach(ISolverSeq o) {
		obs.add(o);
	}

	public void detach(ISolverSeq o) {
		obs.remove(o);
	}

	public void interpreterDone() {

		try {
			interpreteComplete.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void notifySolver() {
		interpreteComplete.release();
	}

	/**
	 * Try to shutdown immediately all ISolverSeq thread that still running. It
	 * assure that they really been canceled.
	 */
	public void killAll() {

		List<Runnable> notFinished = executor.shutdownNow(); // savoir qui a
		if (notFinished == null) {
			System.out.println("they all complete");// fini
		}
		for (Future<Integer> o : fsolvers) {
			if (!o.isDone()) {
				o.cancel(true);
			}
		}
		// Wait interpreters to finish interpret the last results
		interpreterDone();
	}

	public void waitSolver() throws InterruptedException {
		stopInterpretation.acquire();
	}

	public void notifyInterpreter() {
		stopInterpretation.release();
	}

	/**
	 * Run all recorded solvers. Wait for a result, if solver did complete well:
	 * kill 'em all (solvers), if not : repeat wait for the next result
	 */
	public Boolean call() {

		CompletionService<Integer> runSolvers = new ExecutorCompletionService<Integer>(executor);

		for (ISolverSeq o : obs) {
			fsolvers.add(runSolvers.submit(o));
		}

		int i = 0;
		do {

			try {
				// waiting for the first solver to complete
				Future<Integer> solverDone = runSolvers.take();

				// Test if it did complete well
				if (solverDone.get() == 0) {
					break;
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return false;
			}
		} while (i++ < obs.size());

		// Wake interpreterObservable, it joins all interpreters in order to
		// ensure they have completed well
		notifyInterpreter();

		killAll();

		return (i == obs.size()) ? false : true;
	}

}
