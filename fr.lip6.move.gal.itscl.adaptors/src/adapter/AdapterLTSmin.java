package adapter;

 
import fr.lip6.move.gal.itscl.application.ISolverSeq;
import operations.adaptation.IRunner;

public class AdapterLTSmin implements ISolverSeq{

	private final IRunner ltsRunner;
	
	
	public AdapterLTSmin(IRunner lts){
		ltsRunner=lts;
	}

	@Override
	public void currentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isComplete() {
		return ltsRunner.taskDone()?0:1;
	}

	@Override
	public Integer call() throws Exception {
		ltsRunner.solve();
		return isComplete();
	}

}
