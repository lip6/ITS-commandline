package fr.lip6.move.gal.itscl.application;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public interface IListener {
	
	public PipedOutputStream getPout();
	public PipedInputStream getPin();
	public void closePinPout();


}
