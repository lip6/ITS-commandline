package fr.lip6.move.gal.itscl.modele;


public interface IRunner extends IProblem{

	public void solve();

	public Boolean taskDone();
	
	public void setInterpreter();

}
