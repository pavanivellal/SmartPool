package ride;

public class DelayedState implements StateInterface {

	RideInterface ride;
	
	public DelayedState(RideInterface r) {
	  ride = r;
	}

	@Override
	public String startRide(int choice) {
		 return "Ride has been already started";
	}

	@Override
	public String waitingRide(int choice) {		
		return "Ride started!";
	}

	@Override
	public String delayRide(int choice) {
		ride.setState(new StartedState(ride));
		return "Dear customer, sorry for delay. Ride is starting again soon!";	
	}

	@Override
	public String endRide(int choice) {
		return "Ride ended!";
	}

	@Override
	public String cancelRide(int choice) {
		return "Ride is currently delayed and still continuing";
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
