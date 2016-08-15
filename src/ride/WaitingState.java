package ride;

import java.util.Scanner;


public class WaitingState implements StateInterface{

	RideInterface ride;
	
	public WaitingState(RideInterface r) {
		ride = r;
	}
	@Override
	public String startRide(int choice) {		
		return "Ride has been already started and waiting for customers to arrive!";
	}
	
	@Override
	public String waitingRide(int choice) {
		
		switch(choice){
			case 1: ride.setState(new StartedState(ride));
					return "";
							
			case 2: ride.setState(new CancelState(ride));
					return "";
							
			case 3: ride.setState(new DelayedState(ride));
					return "";
			
			case 4: ride.setState(new EndState(ride));
					return "";
			
			default: return "waiting for user input!";
		}				
	}
	
	@Override
	public String delayRide(int choice) {
		return "Driver is currently waiting for customers to arrive!";	
	}
	
	@Override
	public String endRide(int choice) {
		return "Driver is currently waiting for customers to arrive!";		
	}
	@Override
	public String cancelRide(int choice) {
		return "Driver is currently waiting for customers to arrive!";	
	}
	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

}
