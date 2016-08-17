package request;

public class SimpleRequest extends Request {

	private Location srcLocation;
	private Location destLocation;
	private String dateTime = "";
	private int customerID;
	private int driverID;
	private String userName;
	private double fare;
	
	enum carType{
		fiveSeater,
		eightSeater
	};
	
	carType carTyp;

	
	public SimpleRequest() {
	}

	public Location getSource() {		
		return srcLocation;
	}

	public Location getDestination() {
		return destLocation;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public void setCustomerID(int id){
		this.customerID = id;
	}
	public void setDriverID(int id){
		this.driverID = id;
	}
	public int getCustomerID(){
		return this.customerID;
	}
	public int getDriverID(){
		return this.driverID;
	}
	public String getCarType(){
		return this.carTyp.toString();
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setFare(double fare){
		this.fare = fare;
	}
	public double getFare(){
		return this.fare;
	}

	public void acceptSource() {
		System.out.println("Enter the source co-ordinates (X,Y) : ");
		input = keyboard.nextLine();
        numbersStr = input.split(",");		
        srcLocation = new Location(Integer.parseInt(numbersStr[0]),
        									Integer.parseInt(numbersStr[1]));       
	}
	
	public void acceptDestination() {		
		System.out.println("Enter the destination co-ordinates (X,Y) : ");
		input = keyboard.nextLine();
        numbersStr = input.split(",");		
        destLocation = new Location(Integer.parseInt(numbersStr[0]),
        									Integer.parseInt(numbersStr[1]));  		
	}

	public void acceptDateAndTime() {		
		System.out.println("Enter the Date And Time: (MM-DD-YYYY HR:MIN) ");
		input = keyboard.nextLine();
		dateTime = input;
	}
	
	public void acceptUserName() {		
		System.out.println("Enter the user name : ");
		input = keyboard.nextLine();
		userName = input;
	}
	
	public void acceptCarType() {
		System.out.println("Enter the type of a car :");
		System.out.println("1.Five seater");
		System.out.println("2.Eight seater");
		input = keyboard.nextLine();	
		
		if(input.equals("1")){
			carTyp = carType.fiveSeater;
		}
		else if(input.equals("2"))
		{
			carTyp = carType.eightSeater;
		}
		else{
			System.out.println("Invalid Input!");
		}
	}
	
	@Override
	public void display() {		
		Location loc;
		System.out.println("\nRequest generated for a new Ride!!!");
		System.out.println("Request Details: ");
		loc = getSource();
		System.out.println("Source: " + loc.getX() + "," + loc.getY());
		loc = getDestination();
		System.out.println("Destination: " + loc.getX() + "," + loc.getY());
		System.out.println("Date and Time: " + getDateTime());
	}	
	
}
