package specialOffer;
import java.lang.reflect.Array;
import java.util.ArrayList;

import membership.Customer;

public abstract class Offers {
	String members[];

	public abstract void notifySubscriber(String subject);
	public abstract void removeObserver(UserGroup cg);
	public abstract void addObservre(UserGroup cg);

	
}


