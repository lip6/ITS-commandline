package fr.lip6.move.gal.itscl.application;
 
import fr.lip6.move.gal.application.LTSminRunner;

public class AdapterLTSmin implements ISolverSeq{

	private final LTSminRunner ltsRunner;

	
	public AdapterLTSmin(LTSminRunner lts){
		ltsRunner=lts;
	}

	@Override
	public void currentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isComplete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer call() throws Exception {
		ltsRunner.solve();
		return null;
	}
}
