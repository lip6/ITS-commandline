package operations.adaptation;

import adapter.Adapter;
import fr.lip6.move.gal.itscl.application.IRunner;
import fr.lip6.move.gal.itscl.application.ISolver;

public class InteractApplication {

	
	public static ISolver add(IRunner r){
		return new Adapter(r);
	}
	
	
	
	
}
