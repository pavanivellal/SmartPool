package schedular;

import request.Request;
import ride.Ride;

public interface SchedularInterface {
	
	
	public boolean acceptedByDriver(Request req);
	
	public boolean acceptedByCustomer(Request req);
	
	public Ride createRide(Request req);
}
