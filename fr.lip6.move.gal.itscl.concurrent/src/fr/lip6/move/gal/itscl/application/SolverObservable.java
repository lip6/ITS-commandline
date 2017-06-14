package fr.lip6.move.gal.itscl.application;

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

public class SolverObservable implements ISolverObservable {

	private Set<ISolverSeq> obs = new HashSet<>();
	private List<Future<Integer>> fsolvers = new ArrayList<Future<Integer>>();
	private ExecutorService executor = Executors.newCachedThreadPool();

	public void attach(ISolverSeq o) {
		obs.add(o);

	}

	public void detach(ISolverSeq o) {
		obs.remove(o);
	}

	public void killAll() {
		List<Runnable> notFinished = executor.shutdownNow(); // savoir qui a fini
		for (Runnable r : notFinished) {
			System.out.println(r);

		}

		for (Future<Integer> o : fsolvers) {
			if (!o.isCancelled() || !o.isDone()) {
				// Erreur un thread n'a pas ete shutdown
				o.cancel(true);
				System.out.println("i pass hiiieree  why soo");

			}

		}
	}

	public Boolean call() {

		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
//		fsolvers = executor.invokeAll(obs); //execute les solvers et renvoie les futures dans l'ordre qui a ete donné dans obs
		
		for (ISolverSeq o : obs) {
			fsolvers.add(completionService.submit(o));
		}

		int nbSolver = obs.size(), i = 0;

		do {
			try {

				Future<Integer> solverDone = completionService.take();
				if (solverDone.isDone() && solverDone.get() == 0) {
					killAll();
					return true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException ee) {
				ee.printStackTrace();
				return false;
			}
		} while (++i < nbSolver);

		System.out.println("Problem not solved");
		return false;
	}

}
