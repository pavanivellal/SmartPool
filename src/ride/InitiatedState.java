package ride;

import java.util.Scanner;

public class InitiatedState implements iState {

	private iRide ride;
	public InitiatedState(iRide r) {
		ride = r;
	}

	@Override
	public String initiateRide() {
		ride.setState(new WaitingState(ride));
		return "Ride has been initiated";	 
	}
	
	@Override
	public String startRide(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String waitingRide(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delayRide(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String endRide(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelRide(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
