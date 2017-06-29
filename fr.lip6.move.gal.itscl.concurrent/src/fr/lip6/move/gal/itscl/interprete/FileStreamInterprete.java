package fr.lip6.move.gal.itscl.interprete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 
 *
 */
public class FileStreamInterprete{

	private final PipedInputStream pin;

	private PipedOutputStream pout = null;
	private BufferedReader in;

	public FileStreamInterprete() {

		this.pin = new PipedInputStream(4096);

		try {
			this.pout = new PipedOutputStream(pin);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("error pipou");
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
	

	public String getWrittenData() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeIn() throws IOException{
		in.close();
	}
	
}
