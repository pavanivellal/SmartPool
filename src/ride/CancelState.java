package ride;

import java.util.Scanner;

public class CancelState implements StateInterface {

	RideInterface ride;
	public CancelState(RideInterface r) {
		ride = r;
	}
	
	@Override
	public String startRide(int choice) {
		return "Ride Canceled!";
	}

	@Override
	public String waitingRide(int choice) {
		return "Ride Canceled!";
	}

	@Override
	public String delayRide(int choice) {
		return "Ride Canceled!";
	}

	@Override
	public String endRide(int choice) {
		return "Ride Ended!";
	}

	@Override
	public String cancelRide(int choice) {
		ride.setState(new EndState(ride));
		return "Ride Canceled!";
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

}
