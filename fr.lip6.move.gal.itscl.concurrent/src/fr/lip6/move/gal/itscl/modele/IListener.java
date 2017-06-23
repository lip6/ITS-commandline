package fr.lip6.move.gal.itscl.modele;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Callable;

public interface IListener extends Callable<Boolean>{
	
	public PipedOutputStream getPout();
	public PipedInputStream getPin();
	public void closePinPout();


}
