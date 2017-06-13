package operations.adaptation;

import fr.lip6.move.gal.itscl.application.SolverObservable;

public class InteractApplication {

	private SolverObservable sobs;
	
	public InteractApplication(){
		super();
		sobs = new SolverObservable();
	}
	
	public void add(IRunner r){
		sobs.attach(r);
	}
	
	public void remove(IRunner r){
		sobs.detach(r);
	}
	
	
	
}
