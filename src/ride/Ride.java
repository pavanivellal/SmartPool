package ride;

public class Ride implements RideInterface {

	private StateInterface state;
	private static int id;
	private int customer1_id;
	private int customer2_id;
	private int customer3_id;
	private int driver_id;
	private String status;
	private String start_time;
	private String end_time;
	private double fare;
	
	
	public Ride() {
		state = new InitiatedState(this);	
		id++;
	}

	@Override
	public void initiateRide() {
		System.out.println(state.initiateRide());
	}
	
	@Override
	public void startRide(int choice) {
		System.out.println(state.startRide(choice));
	}

	@Override
	public void waitingRide(int choice) {
		System.out.println(state.waitingRide(choice));		
	}

	@Override
	public void delayRide(int choice) {
		System.out.println(state.delayRide(choice));		
	}

	@Override
	public void endRide(int choice) {
		System.out.println(state.endRide(choice));			
	}

	@Override
	public void cancelRide(int choice) {
		System.out.println(state.cancelRide(choice));				
	}

	@Override
	public void setState(StateInterface s) {
		state = s;	
	}

	@Override
	public StateInterface getState() {
		
		return state;
	}

	public int getRideID(){
		return id;
	}
	
	public void setCustomer1_id(int id){
		customer1_id = id;
	}
	
	public void setCustomer2_id(int id){
		customer2_id = id;
	}
	
	public void setCustomer3_id(int id){
		customer3_id = id;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Ride.id = id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double d) {
		this.fare = d;
	}

	public int getCustomer1_id() {
		return customer1_id;
	}

	public int getCustomer2_id() {
		return customer2_id;
	}

	public int getCustomer3_id() {
		return customer3_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ride details: " +"\n"+ "Ride id: "+ id +", Customer1_id: " + customer1_id + ", Customer2_id: " + customer2_id
				+ ", Customer3_id: " + customer3_id + ", Driver_id: " + driver_id +  ", Start time: "
				+ start_time + ", End_time: " + end_time + ", Fare ($): " + fare + ", Status: " + status;
	}
}
