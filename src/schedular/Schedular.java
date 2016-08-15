package schedular;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import DAO.MySQLAccess;
import membership.Driver;
import notification.CustomerNotification;
import notification.EmailNotification;
import notification.Message;
import notification.NotificationCenter;
import payment.CalculatePayment;
import payment.CardPayment;
import payment.ClosedParking;
import payment.EconomyCar;
import payment.EstimatePayment;
import payment.OnlinePayment;
import payment.OpenParking;
import payment.Parking;
import payment.Payment;
import payment.LuxuryCar;
import request.AddVehicleType;
import request.Location;
import request.ProvideAssistance;
import request.Request;
import request.SimpleRequest;
import ride.CancelState;
import ride.DelayedState;
import ride.Ride;
import route.Utility;
import route.RoutingContext;

public class Schedular implements SchedularInterface {

	Request req;
	private Queue reqQueue = new LinkedList();
	private Queue matchingReqQueueLocation = new LinkedList();
	private Queue matchingReqQueueCarType = new LinkedList();

	MappingStrategy mapStrategy;

	public Schedular() {
		// TODO Auto-generated constructor stub
	}

	public Request removeElementFromQueue() {
		return (Request) reqQueue.remove();
	}

	public Queue getReqQueue() {
		return reqQueue;
	}

	public void recieveRequest() {

		SimpleRequest req = new SimpleRequest();
		req.acceptSource();
		req.acceptDestination();
		//req.acceptDateAndTime();
		req.acceptCarType();
		req.acceptUserName();
		reqQueue.add(req);
		
		SimpleRequest req2 = new SimpleRequest();
		req2.acceptSource();
		req2.acceptDestination();
		//req.acceptDateAndTime();
		req2.acceptCarType();
		req2.acceptUserName();
		reqQueue.add(req2);
		
		/*SimpleRequest req3 = new SimpleRequest();
		req3.acceptSource();
		req3.acceptDestination();
		//req.acceptDateAndTime();
		req3.acceptCarType();
		req3.acceptUserName();
		reqQueue.add(req3);
		
		//req.setUserName(userName);
		//reqQueue.add(req);

		/*AddVehicleType vehicleType = new AddVehicleType(req);
		vehicleType.acceptCarType();*/

		/*ProvideAssistance assistance = new ProvideAssistance(req);
		assistance.provideNeededAssistance();*/

		//reqQueue.add(req);

		/*EstimatePayment estimate = new EstimatePayment(3,2,"Luxury");
		estimate.cost_for_distance();
		
        DoPayment(3, 25, "Luxury");*/
		MySQLAccess db = new MySQLAccess();
		 
		DriverAndRequestMapping map1 = new DriverAndRequestMapping();
		map1.MapDriverAndRequest(reqQueue,db.getAllDrivers());
		
	/*	MappingContext mappingCtx = new MappingContext();

		mapStrategy = mappingCtx.setMappingStrategy("cartype", reqQueue);
		matchingReqQueueCarType = mapStrategy.MapDriverAndRequest(reqQueue);

		mapStrategy = mappingCtx.setMappingStrategy("location", reqQueue);
		matchingReqQueueLocation = mapStrategy.MapDriverAndRequest(reqQueue);*/

		/*Ride ride = new Ride();
		ride.initiateRide();
		int choice;
		Scanner keyboard = new Scanner(System.in);
		String input = "";

		do {
			System.out.println("\n");
			System.out.println("1.Start Ride.. ");
			System.out.println("2.Notify customer about the ride cancelation..");
			System.out.println("3.Notify customer about the delay.. ");
			System.out.println("4.End Ride..");
			System.out.println("5.Exit..");

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

			case 5:
				break;

			default:
				break;
			}
		} while (choice != 5);*/
	}

	public void NotifyCustomer(Request req) {

		NotificationCenter notify;
		Message message;

		message = new EmailNotification();
		notify = new CustomerNotification(message, "Request Approved for customer: ");
	}

	@Override
	public boolean acceptedByDriver(Request req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptedByCustomer(Request req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ride createRide(Request req) {
		// TODO Auto-generated method stub
		return null;
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
	
	public void DoPayment(int no_of_memb, double dist, String carType)
	{
			
			System.out.println("Enter no. of members, distance in miles, CarType");
			
			Scanner scan = new Scanner(System.in);
			double park_hrs;
			String parkType;
			
			Payment pay1, pay2;
			CalculatePayment Typ1, Typ2, Typ3;
			CardPayment c1;
			OnlinePayment op1;
			Parking prk;
			
			
			
			if (carType.equalsIgnoreCase("Economy"))
			{
				
				Typ1 = new EconomyCar(dist,no_of_memb);
				Typ1.cost_for_distance();
				System.out.println("Specify Parking Type\n1.Covered\n2.Open");
				parkType = scan.next();
				System.out.println("Specify Parking Hours?");
				park_hrs  = scan.nextDouble();
				switch(parkType)
				{
				case "Covered":
				case "1":
					OpenParking p1 = new OpenParking(Typ1,no_of_memb);
					p1.addCharges(park_hrs);
					break;
					
				case "Open":
				case "2":
					ClosedParking p2 = new ClosedParking(Typ1,no_of_memb);
					p2.addCharges(park_hrs);
					break;				
					
				default: 
					System.out.println("\nIncorrect Option. Payment not made");
					break;			
				}
				
				System.out.println("Choose Payment options:\n1.Card Payment \n2.Online Payment");
				String pay_type = scan.next();
				if(pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment") )
				{
					c1 = new CardPayment(Typ1, no_of_memb);
					c1.make_payment();
					
				}
				else if (pay_type.equals("2") || pay_type.equals("Online Payment"))
				{
					op1 = new OnlinePayment(Typ1, no_of_memb);
					op1.make_payment();
				}
				else
				{
					System.out.println("\nIncorrect Option");
				}
				
				
			}
			
			else if (carType.equalsIgnoreCase("Luxury"))
			{
				Typ2 = new EconomyCar(dist,no_of_memb);
				Typ2.cost_for_distance();
				System.out.println("Specify Parking Type\n1.Covered\n2.Open");
				parkType = scan.next();
				System.out.println("Specify Parking Hours?");
				park_hrs  = scan.nextDouble();
				switch(parkType)
				{
				case "Covered":
				case "1":
					OpenParking p1 = new OpenParking(Typ2,no_of_memb);
					p1.addCharges(park_hrs);
					break;
					
				case "Open":
				case "2":
					ClosedParking p2 = new ClosedParking(Typ2,no_of_memb);
					p2.addCharges(park_hrs);
					break;				
					
				default: 
					System.out.println("\nIncorrect Option. Payment not made");
					break;			
				}
				
				System.out.println("Choose Payment options:\n1.Card Payment \n2.Online Payment");
				String pay_type = scan.next();
				if(pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment") )
				{
					c1 = new CardPayment(Typ2, no_of_memb);
					c1.make_payment();
					
				}
				else if (pay_type.equals("2") || pay_type.equals("Online Payment"))
				{
					op1 = new OnlinePayment(Typ2, no_of_memb);
					op1.make_payment();
				}
				else
				{
					System.out.println("\nIncorrect Option");
				}

				
			}
	}
}
