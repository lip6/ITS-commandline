package adapter;


import fr.lip6.move.gal.itscl.application.ISolverSeq;
import operations.adaptation.IRunner;

public class AdapterITS  implements ISolverSeq{

	
	private IRunner itsRunner;

	public AdapterITS(IRunner its) {
		itsRunner=its;
	}
	@Override
	public void currentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isComplete() {
		return itsRunner.taskDone()?0:1;

	}

	@Override
	public Integer call() throws Exception {
		itsRunner.solve();
		return isComplete();
	}
}
