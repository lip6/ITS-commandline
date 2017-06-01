package fr.lip6.move.gal.itscl.application;

public class  ItsSolver implements ISolver  {
	
	synchronized public ResolveProblem solve(Problem p){
		
		return new ResolveProblem(ResolveProblem.UNKNOWN);

	}
	
}
