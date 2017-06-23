package fr.lip6.move.gal.itscl.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class ListenerRunner implements IListener {
	protected final PipedInputStream pin;

	protected PipedOutputStream pout = null;
	protected BufferedReader in;

	public ListenerRunner(int pipeSize) {

		this.pin = new PipedInputStream(pipeSize);
		try {
			this.pout = new PipedOutputStream(pin);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.in = new BufferedReader(new InputStreamReader(pin));

	}

	public PipedOutputStream getPout() {
		return pout;
	}

	public PipedInputStream getPin() {
		return pin;
	}

	public void closePinPout() {
		try {
			pin.close();
			pout.close();
		} catch (IOException e) {
			System.out.println("Problemo uno fermento dilistener");
			e.printStackTrace();
		}
	}


}
