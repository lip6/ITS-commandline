package adapter;


import fr.lip6.move.gal.itscl.application.ISolverSeq;
import operations.adaptation.IRunner;

public class AdapterSMTR implements ISolverSeq{

	
	private IRunner smtRunner;
	
	public AdapterSMTR(IRunner smt){
		smtRunner=smt;
	}
	
	public void currentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isComplete() {
		return smtRunner.taskDone()?0:1;
	}

	@Override
	public Integer call() throws Exception {
		smtRunner.solve();
		return null;
	}
	

}
