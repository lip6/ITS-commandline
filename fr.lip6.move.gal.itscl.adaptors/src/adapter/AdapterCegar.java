package adapter;


import fr.lip6.move.gal.itscl.application.ISolverSeq;
import operations.adaptation.IRunner;

public class AdapterCegar extends Adapter implements ISolverSeq{

	
	public AdapterCegar(IRunner ceg){
		runner=ceg;
	}



	public void currentState() {
		
	}

	public int isComplete() {
		return runner.taskDone()?0:1;
	}

 
	public Integer call() throws Exception {
		
		runner.solve();
		
		return isComplete();
		
	}
	
	
	
	
}
