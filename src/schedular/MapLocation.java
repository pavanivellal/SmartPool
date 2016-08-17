package schedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import request.Location;
import request.Request;
import request.SimpleRequest;
import route.RoutingContext;
import route.Utility;
import vehicle.Car;

public class MapLocation implements MappingStrategy {

	public HashMap<String,Queue> MapDriverAndRequest(Queue reqQueue, Queue driverQueue, String driverName) {

		Queue reqTempQueue = new LinkedList();
		Queue reqTempQueue1 = new LinkedList();
		Queue driverQueue1 = new LinkedList();
		Driver driver1 = null, driver = null;
		MySQLAccess db = new MySQLAccess();
		HashMap hm = new HashMap<>();

		// Get first request's destination
		SimpleRequest reqTemp = (SimpleRequest) reqQueue.element();
		Location commonDestination = reqTemp.getDestination();
		SimpleRequest req;

		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		ListIterator<Driver> driverIterator = (ListIterator<Driver>) driverQueue.iterator();
		// Compare it with every other request to find the match
		while (listIterator.hasNext()) {
			req = (SimpleRequest) listIterator.next();
			if (req.getDestination().getX() == commonDestination.getX()
					&& req.getDestination().getY() == commonDestination.getY()
					&& req.getCarType() == reqTemp.getCarType()) {
				reqTempQueue.add(req);
			}
		}

		listIterator = (ListIterator<Request>) reqTempQueue.iterator();

		while (listIterator.hasNext()) {

			req = (SimpleRequest) listIterator.next();

			while (driverIterator.hasNext()) {
				driver = (Driver) driverIterator.next();
				Car car = (Car) driver.getVehicle();
				if (req.getCarType().equalsIgnoreCase(car.getModel())) {
					driverQueue1.add(driver);
				}
			}
		}

		SimpleRequest req1 = (SimpleRequest) reqTempQueue.element();
		Location commonSource = req1.getSource();

		int closestDriverDistance = 1000000;
		Driver closestDriver;

		driverIterator = (ListIterator<Driver>) driverQueue1.iterator();
		while (driverIterator.hasNext()) {
			driver1 = (Driver) driverIterator.next();
			int distance = FindRoutes(commonSource.getX(), commonSource.getY(), driver1.getX(), driver1.getY());
			if (distance < closestDriverDistance) {
				closestDriverDistance = distance;
				closestDriver = driver1;
			}
		}

		/*if (reqTempQueue.size() >= 2 && driver.getUserName().equalsIgnoreCase(driverName)) {
			listIterator = (ListIterator<Request>) reqTempQueue.iterator();
			Customer cust;

			System.out
					.println("Requests are Matching with Driver " + driver.getFirstName() + " " + driver.getLastName());
			System.out.println("\n" + reqTempQueue.size() + " Customer request are matching with the you! ");
			System.out.println("\n List of Matching Customers Requests => ");

			while (listIterator.hasNext()) {
				req = (SimpleRequest) listIterator.next();
				req.setDriverID(driver1.getMemberId());
				cust = db.getCustomerByUserName(req.getUserName());
				System.out.println(cust.getFirstName() + " " + cust.getLastName());
			}
			hm.put(driver.getUserName(),reqTempQueue);
			return (HashMap<String, Queue>) hm;
		} else {
			System.out.println(
					" Number of matching Customer request : " + reqTempQueue.size() + ". Carpool scheduling not possible!");
		}
		return hm;*/
		
		if (driver != null) {

			if (reqTempQueue.size() >= 2 && driver.getUserName().equalsIgnoreCase(driverName)) {
				/*
				 * System.out.println("Requests are Matching with Driver " +
				 * driver.getFirstName() + " " + driver.getLastName());
				 * listIterator = (ListIterator<Request>)
				 * reqTempQueue.iterator();
				 * 
				 * Customer cust; System.out.println("\n" + reqTempQueue.size()
				 * + " Customer request are matching with the you! ");
				 * System.out.println(
				 * "\n List of Matching Customers Requests => "); while
				 * (listIterator.hasNext()) { req = (SimpleRequest)
				 * listIterator.next(); req.setDriverID(driver.getMemberId());
				 * req.setCustomerID(db.getCustomerByUserName(req.getUserName())
				 * .getMemberId()); cust =
				 * db.getCustomerByUserName(req.getUserName());
				 * System.out.println(cust.getFirstName() + " " +
				 * cust.getLastName()); }
				 */
				hm.put(driver.getUserName(), reqTempQueue);
			} else {
				System.out.println("Sorry, Number of matching Customer request : " + reqTempQueue.size()
						+ ". Carpool scheduling is not possible!");
			}
		}
		return hm;
	}

	public int FindRoutes(int source_i, int source_j, int dest_i, int dest_j) {

		Utility utility = new Utility();
		int source = utility.locateNode(source_i, source_j);
		int destination = utility.locateNode(dest_i, dest_j);

		RoutingContext rc = new RoutingContext();

		int optimalRoute = rc.route(source, destination);
		return optimalRoute;
	}

	public Location findCommonSource(Queue reqQueue) {

		Location srcLocation = ((SimpleRequest) reqQueue.remove()).getSource();
		return srcLocation;
	}

}
