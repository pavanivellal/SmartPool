package ride;

import java.util.Scanner;

public class InitiatedState implements StateInterface {

	private RideInterface ride;
	public InitiatedState(RideInterface r) {
		ride = r;
	}

	@Override
	public String initiateRide() {
		Scanner keyboard = new Scanner( System.in );
		String input = "";
		
		/*System.out.println("1.Start Ride.. ");
		System.out.println("2.Notify customer about the delay.. ");
		System.out.println("3.Notify customer about the car breakdown..");
		System.out.println("4.Notify customer about the car breakdown..");
		System.out.println("5.End ride..");
		input = keyboard.nextLine();	
		
		if(input.equals("1")){
			ride.setState(new DelayedState(ride));
			return "Dear customer, sorry for delay. Ride will arrive soon!";	
		}
		else if(input.equals("2"))
		{
			ride.setState(new CancelState(ride));
			return "Canceling the ride!";
		}
		else if(input.equals("3")){
			ride.setState(new CancelState(ride));
			return "Canceling the ride due to car breakdown!";
		}
		else if(input.equals("4")){
			ride.setState(new StartedState(ride));
			return "Ride is going to start soon!";
		}
		else
		{
			return "";
		}*/
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
