package schedular;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import request.Location;
import request.Request;
import request.SimpleRequest;
import route.Utility;
import route.RoutingContext;
import vehicle.Car;

public class DriverAndRequestMapping {

	public DriverAndRequestMapping() {
		// TODO Auto-generated constructor stub
	}
	
	public Queue MapDriverAndRequest(Queue reqQueue, Queue driverQueue){
		
		Queue reqTempQueue = new LinkedList();
		Queue reqTempQueue1 = new LinkedList();
		Queue driverQueue1 = new LinkedList();
		Driver driver1 = null, driver = null;
		MySQLAccess db = new MySQLAccess();
		
		//Get first request's destination
		SimpleRequest reqTemp = (SimpleRequest) reqQueue.element();
		Location commonDestination = reqTemp.getDestination();	
		SimpleRequest req;
		
		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		//Compare it with every other request to find the match
		while(listIterator.hasNext()){
			req = (SimpleRequest) listIterator.next();
			if(req.getDestination().getX() == commonDestination.getX() &&
			   req.getDestination().getY() == commonDestination.getY() &&
			   req.getCarType() == reqTemp.getCarType()){
					reqTempQueue.add(req);
				}					
		}
		
		ListIterator<Request> listIterator1 = (ListIterator<Request>) reqTempQueue.iterator();
		//ListIterator<Driver> driverIterator = (ListIterator<Driver>) driverQueue.iterator();
	
		while(listIterator1.hasNext()){
			
			ListIterator<Driver> driverIterator = (ListIterator<Driver>) driverQueue.iterator();
			req = (SimpleRequest) listIterator1.next();
			
			while(driverIterator.hasNext()){
			driver = (Driver) driverIterator.next();
			Car car = (Car) driver.getVehicle();			
			if(req.getCarType().equalsIgnoreCase(car.getModel())){
					driverQueue1.add(driver);
				}					
			}
		}
		
		SimpleRequest req1 = (SimpleRequest) reqTempQueue.element();
		Location commonSource = req1.getSource();
		
		ListIterator<Driver> driverIterator1 = (ListIterator<Driver>) driverQueue1.iterator();
		
		int closestDriverDistance = 1000000;
		Driver closestDriver;
		
		while(driverIterator1.hasNext()){
			driver1 = (Driver) driverIterator1.next();
			//Car car = (Car) driver.getVehicle();
			int distance = FindRoutes(commonSource.getX(),commonSource.getY(),driver1.getX(),driver1.getY());
			if(distance < closestDriverDistance){		
				closestDriverDistance = distance;
				closestDriver = driver1;
			}				
		}
		
		
	  /*	int noOfReq = reqTempQueue.size() - 1;
		if(noOfReq == 1){		
			System.out.println(noOfReq  + " Customer Request is Matching with Driver " +  "");				
		}
		else
		{
			System.out.println(noOfReq + " Customers Requests are Matching with Driver " +  "");	
		}*/
		
		System.out.println("Requests are Matching with Driver " +  driver1.getFirstName() + " " + driver1.getLastName());
		

		ListIterator<Request> listIterator2 = (ListIterator<Request>) reqTempQueue.iterator();
		
		Customer cust;
		System.out.println("\n List of Customers Requests ");
		while(listIterator2.hasNext()){
			req = (SimpleRequest)listIterator2.next();
			cust = db.getCustomerByUserName(req.getUserName());
			System.out.println(cust.getFirstName() + " " + cust.getLastName());
		}			
		return reqTempQueue;			
	}	
	
	public int FindRoutes(int source_i, int source_j, int dest_i, int dest_j) {

		Utility utility = new Utility();
		int source = utility.locateNode(source_i, source_j);
		int destination = utility.locateNode(dest_i, dest_j);

		RoutingContext rc = new RoutingContext();

		int optimalRoute = rc.route(source, destination);
		return optimalRoute;
	}
	
	public Location findCommonSource(Queue reqQueue){
		
		Location srcLocation = ((SimpleRequest)reqQueue.remove()).getSource();
		return srcLocation;	
	}

}
