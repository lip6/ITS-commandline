package fr.lip6.move.gal.itscl.interpreter;

import java.io.ByteArrayOutputStream;


/**
 * Interpreters that use ByteArray I/O stream
 */
public class InterpreteBytArray {

	ByteArrayOutputStream pout;

	public InterpreteBytArray() {
		pout = new ByteArrayOutputStream();
	}

	public String getWrittenData() {
		return pout.toString();
	}

	public ByteArrayOutputStream getPout() {
		return pout;
	}

}
