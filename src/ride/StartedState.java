package ride;

import java.util.Scanner;

public class StartedState implements iState{

	private iRide ride;
	public StartedState(iRide r) {
		ride = r;
	}

	@Override
	public String startRide(int choice) {		
		ride.setState(new WaitingState(ride));
		return "Ride Started!";					
	}

	@Override
	public String waitingRide(int choice) {		
		return "Ride started!";		
	}

	@Override
	public String delayRide(int choice) {
		
		return "Ride started!";
		
	}

	@Override
	public String endRide(int choice) {
		
		return "Ride started!";
		
	}

	@Override
	public String cancelRide(int choice) {
		return "Ride started!";
		
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

}
