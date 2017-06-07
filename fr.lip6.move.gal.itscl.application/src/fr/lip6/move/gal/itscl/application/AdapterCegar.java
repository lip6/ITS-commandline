package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.application.CegarRunner;
import fr.lip6.move.gal.application.Ender;

public class AdapterCegar implements ISolverSeq{

	private final CegarRunner cegRunner;
	
	public AdapterCegar(CegarRunner ceg){
		this.cegRunner=ceg;
	}


	public void solve(Ender obs) {
		cegRunner.runCegar( cegRunner.getSpec(), cegRunner.getFolder(), obs);
	}
	
	
	
	
}
