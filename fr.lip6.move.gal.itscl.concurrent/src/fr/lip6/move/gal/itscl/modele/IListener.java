package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

public interface IListener extends Callable<Object>{
	
	public void setBuffWriterInOut(ItsInterpreter b);
}
