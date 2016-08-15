package ride;

public interface RideInterface {
	public void initiateRide();
	public void startRide(int choice);
	public void waitingRide(int choice);
	public void delayRide(int choice);
	public void endRide(int choice);
	public void cancelRide(int choice);
	public void setState(StateInterface s);
	public StateInterface getState();
}
