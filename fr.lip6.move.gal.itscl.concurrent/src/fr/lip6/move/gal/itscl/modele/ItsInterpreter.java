package fr.lip6.move.gal.itscl.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ItsInterpreter {
	private final PipedInputStream pin;

	private PipedOutputStream pout = null;
	private BufferedReader in;

	public ItsInterpreter(int pipeSize) {

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

	public String readLine() throws IOException {
		return in.readLine();
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

	public void closeIn() throws IOException {
		in.close();
	}

}
