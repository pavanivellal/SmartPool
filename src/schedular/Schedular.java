package schedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;
import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import notification.CustomerNotification;
import notification.EmailNotification;
import notification.Message;
import notification.NotificationCenter;
import parking.Parking;
import payment.CalculatePayment;
import payment.CardPayment;
import payment.FiveSeaterCar;
import payment.EstimatePayment;
import payment.OnlinePayment;
import payment.Payment;
import request.AddVehicleType;
import request.Location;
import request.ProvideAssistance;
import request.Request;
import request.SimpleRequest;
import ride.CancelState;
import ride.DelayedState;
import ride.Ride;
import route.RoutingContext;
import route.Utility;

public class Schedular implements iSchedular {

	Request req;
	private Queue reqQueue = new LinkedList();
	private Queue matchingReqQueueLocation = new LinkedList();
	private Queue matchingReqQueueCarType = new LinkedList();
	private Queue matchedReqQueue = new LinkedList();
	Scanner keyboard = new Scanner(System.in);
	String input = "";
	String driverName;
	
	ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();

	MappingStrategy mapStrategy;

	public Schedular(Queue reqQueue, String driverName) {
		this.reqQueue = reqQueue;
		this.driverName = driverName;
	}

	public Request removeElementFromQueue() {
		return (Request) reqQueue.remove();
	}

	public Queue getReqQueue() {
		return reqQueue;
	}
	
	@Override
	public void scheduleRide() {	

		/*
		 * EstimatePayment estimate = new EstimatePayment(3,2,"Luxury");
		 * estimate.cost_for_distance();
		 * 
		 * DoPayment(3, 25, "Luxury");
		 */
		
		MySQLAccess db = new MySQLAccess();

		MappingContext mappingCtx = new MappingContext();

		HashMap<String,Queue> hm = new HashMap<>();
		
		if (checkAssistanceNeed(reqQueue)) {
			hm = (HashMap<String, Queue>) mappingCtx.MapDriverAndRequest("assistanceNeeded", reqQueue, 
										 					 db.getAllDrivers(), driverName);
		} else {
			hm = (HashMap<String, Queue>) mappingCtx.MapDriverAndRequest("assistanceNotNeeded", reqQueue,
															db.getAllDrivers(), driverName);
		}
		
		if(hm.containsKey(driverName)){
		matchedReqQueue = hm.get(driverName);
		if (matchedReqQueue.size() >= 2) {
			System.out.println("\n Do you want to accept the ride?");
			input = keyboard.nextLine();
			if (input.equalsIgnoreCase("y")) {

				//Notify Customers about request acceptance
				NotifyRideAcceptance(matchedReqQueue);				
				Ride ride = new Ride(matchedReqQueue);
				ride.initiateRide();
				int choice;

				do {
					System.out.println("\n");
					System.out.println("\n ************Driver Menu*********");
					System.out.println("1.Start Ride ");
					System.out.println("2.Notify customer about the car cancelation due to failed car");
					System.out.println("3.Notify customer about the delay ");
					System.out.println("4.End Ride");
					
					System.out.println("\n ************Customer Menu*********!");
					System.out.println("5.Track the ride.");
					System.out.println("0.Back to Main menu");

					input = keyboard.nextLine();

					choice = Integer.parseInt(input);

					ride.waitingRide(choice);

					switch (choice) {
					case 1:
						ride.startRide(choice);
						break;

					case 2:
						ride.cancelRide(choice);
						break;

					case 3:
						ride.delayRide(choice);
						break;

					case 4:
						ride.endRide(choice);
						break;

					/*case 5:
						 System.out.println("Current ride state is : " + ride.getStateName());
						 break;*/
						 
					case 0:break;
						
					default:
						break;
					}
				} while (choice != 0);
			}

		} else {
			
			System.out.println("\n Sorry! No requests are currently matchign with you!");
		}
		}
	}
	
	
	private boolean checkAssistanceNeed(Queue reqQueue){		
		listIterator = (ListIterator<Request>) reqQueue.iterator();
		boolean flag = false;
		while(listIterator.hasNext()){
			Request req = listIterator.next();
			if(req.getAssistanceNeed()){
				flag = true;
			}
		}					
		return flag;	
	}

	private void NotifyRideAcceptance(Queue reqQueue){
		
	   listIterator = (ListIterator<Request>) reqQueue.iterator();
		while(listIterator.hasNext()){
			SimpleRequest req = (SimpleRequest) listIterator.next();
			NotifyCustomer(req);
		}		
	}
	

	public void NotifyCustomer(SimpleRequest req) {
		MySQLAccess da = new MySQLAccess();
		NotificationCenter notify;
		Message message;
		message = new EmailNotification();
		Driver driver = da.getDriverById(req.getDriverID());
		
		Customer cust = da.getCustomerByUserName(req.getUserName());
		
		notify = new CustomerNotification(message, "Dear" + cust.getFirstName() +" " + driver.getLastName()+ ", "
				 + "Your Ride Request has been Approved!"
				 + "\n Ride Details =>" 
				 + "\n Date and Time: " + req.getDateTime()
				 + "\n Drive Name: " + driver.getFirstName()+" " + driver.getLastName()
				 + "\n Pick up point: " 
				 + "\n Fare Estimation: " + req.getFare() 
				 +"\nDriver will wait only upto" + "" + " mins!");
		notify.memberNotification();
	}


	public void FindClosestCustomers() {

		for (int i = 0; i < reqQueue.size(); i++) {
			SimpleRequest req = (SimpleRequest) reqQueue.remove();
			Location src = req.getSource();
			Location dest = req.getDestination();
			FindRoutes(src.getX(), src.getY(), dest.getX(), dest.getY());
		}
	}

	public void FindRoutes(int source_i, int source_j, int dest_i, int dest_j) {

		Utility utility = new Utility();
		int source = utility.locateNode(source_i, source_j);
		int destination = utility.locateNode(dest_i, dest_j);

		RoutingContext rc = new RoutingContext();

		int optimalRoute = rc.route(source, destination);
		System.out.println(optimalRoute);
	}

	public void DoPayment(int no_of_memb, double dist, String carType) {

		System.out.println("Enter number of members, distance in miles, CarType");

		Scanner scan = new Scanner(System.in);
		double park_hrs;
		String parkType;

		Payment pay1, pay2;
		CalculatePayment Typ1, Typ2, Typ3;
		CardPayment c1;
		OnlinePayment op1;
		double tot_ind_cost;

		if (carType.equalsIgnoreCase("FiveSeater")) {

			Typ1 = new FiveSeaterCar(dist, no_of_memb);
			Typ1.cost_for_distance();
			System.out.println("Specify Parking Type\n1.Covered\n2.Open");
			parkType = scan.next();
			System.out.println("Specify Parking Hours?");
			park_hrs = scan.nextDouble();
			
			Parking prk = new Parking(parkType, park_hrs, 3, Typ1.tot_cost);
			tot_ind_cost = Typ1.ind_cost + prk.getParkingVal();
			System.out.println("Total cost with parking per individual : " + tot_ind_cost);

			System.out.println("Choose Payment options:\n1.Card Payment \n2.Online Payment");
			String pay_type = scan.next();
			if (pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment")) {
				c1 = new CardPayment(Typ1, no_of_memb);
				c1.make_payment();

			} else if (pay_type.equals("2") || pay_type.equals("Online Payment")) {
				op1 = new OnlinePayment(Typ1, no_of_memb);
				op1.make_payment();
			} else {
				System.out.println("\nIncorrect Option");
			}
		}

		else if (carType.equalsIgnoreCase("EightSeater")) {
			Typ2 = new FiveSeaterCar(dist, no_of_memb);
			Typ2.cost_for_distance();
			System.out.println("Specify Parking Type\n1.Covered\n2.Open");
			parkType = scan.next();
			System.out.println("Specify Parking Hours?");
			park_hrs = scan.nextDouble();
			Parking prk = new Parking(parkType, park_hrs, 3, Typ2.tot_cost);
			tot_ind_cost = Typ2.ind_cost + prk.getParkingVal();
			System.out.println("Total cost with parking per individual : " + tot_ind_cost);

			System.out.println("Choose Payment options:\n1.Card Payment \n2.Online Payment");
			String pay_type = scan.next();
			if (pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment")) {
				c1 = new CardPayment(Typ2, no_of_memb);
				c1.make_payment();

			} else if (pay_type.equals("2") || pay_type.equals("Online Payment")) {
				op1 = new OnlinePayment(Typ2, no_of_memb);
				op1.make_payment();
			} else {
				System.out.println("\nIncorrect Option");
			}

		}
	}

	private void displayRide(Ride r) {

		// System.out.println(r.);
	}	
}
