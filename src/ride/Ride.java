package ride;

import java.util.ListIterator;
import java.util.Queue;

import request.Location;
import request.Request;
import request.SimpleRequest;

public class Ride implements iRide {

	private iState state;
	private static int id;
	private int[] customer_ids = new int[3];
	private int driver_id;
	private String status;
	private String start_time;
	private String end_time;
	private double fare;
	private Location srcLocation;
	private Location destLocation;
	private String stateName;
	
	
	public Ride(Queue matchedReqAndDriverQueue) {
		state = new InitiatedState(this);
		
		setParams(matchedReqAndDriverQueue);
		id++;	
	}
	
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Ride.id = id;
	}

	public int[] getCustomer_ids() {
		return customer_ids;
	}

	public void setCustomer_ids(int[] customer_ids) {
		this.customer_ids = customer_ids;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Location getSrcLocation() {
		return srcLocation;
	}

	public void setSrcLocation(Location srcLocation) {
		this.srcLocation = srcLocation;
	}

	public Location getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(Location destLocation) {
		this.destLocation = destLocation;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	private void setParams(Queue matchedReqAndDriverQueue){
		ListIterator<Request> listIterator = (ListIterator<Request>) matchedReqAndDriverQueue.iterator();
		int i = 0;
		while(listIterator.hasNext()){
			SimpleRequest req = (SimpleRequest) listIterator.next();
			customer_ids[i++] = req.getCustomerID();	
			driver_id = req.getDriverID();
			start_time = req.getDateTime();
			fare = req.getFare();
			srcLocation = req.getSource();
			destLocation = req.getDestination();
		}		
	}

	@Override
	public void initiateRide() {
		System.out.println(state.initiateRide());
		setStateName("Ride Initiated!");
	}
	
	@Override
	public void startRide(int choice) {
		System.out.println(state.startRide(choice));
		setStateName("Ride Started!");
	}

	@Override
	public void waitingRide(int choice) {
		System.out.println(state.waitingRide(choice));	
	}

	@Override
	public void delayRide(int choice) {
		System.out.println(state.delayRide(choice));
		setStateName("Ride Delayed!");
	}

	@Override
	public void endRide(int choice) {
		System.out.println(state.endRide(choice));		
		setStateName("Ride Ended!");
	}

	@Override
	public void cancelRide(int choice) {
		System.out.println(state.cancelRide(choice));	
		setStateName("Ride Canceled!");
	}

	@Override
	public void setState(iState s) {
		state = s;	
	}

	@Override
	public iState getState() {	
		return state;
	}

	public int getRideID(){
		return id;
	}	
	
	public void setEndTime(){

	}
	public double getFare(){
		return fare;
	}
	public Location getSource() {		
		return srcLocation;
	}

	public Location getDestination() {
		return destLocation;
	}
	
	public void setStateName(String inputStateName){
		stateName = inputStateName;
	}
	public String getStateName(){
		return stateName;
	}
}
