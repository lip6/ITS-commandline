package fr.lip6.move.gal.itscl.interprete;

import java.io.ByteArrayOutputStream;

public class InterpreteBytArray {
	
	
	ByteArrayOutputStream pout;
	
	public InterpreteBytArray(){
		pout=new ByteArrayOutputStream();
	}
	
	
	public String getWrittenData(){
		return pout.toString();
	}
	

	public ByteArrayOutputStream getPout() {
		return pout;
	}

}
