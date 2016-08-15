package specialOffer;
import java.lang.reflect.Array;
import java.util.ArrayList;

import membership.Customer;

public abstract class Offers {
	String members[];

	public abstract void notifySubscriber(ArrayList<Customer> customer, String subject);
	
}


