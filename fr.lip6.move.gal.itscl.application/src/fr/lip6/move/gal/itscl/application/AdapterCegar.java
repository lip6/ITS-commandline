package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.Property;
import fr.lip6.move.gal.application.CegarRunner;

public class AdapterCegar implements ISolverSeq{

	private final CegarRunner cegRunner;
	
	public AdapterCegar(CegarRunner ceg){
		this.cegRunner=ceg;
	}



	public void currentState() {
		
	}


	public int isComplete() {
		for (Property prop : cegRunner.getSpec().getProperties()) {
			if (! cegRunner.getDoneProps().contains(prop.getName())) {
				// still some work to do
				return 1;
			}
		}	
		return 0;
	}

 
	public Integer call() throws Exception {
		
		cegRunner.solve();
		
		return isComplete();
		
	}
	
	
	
	
}
