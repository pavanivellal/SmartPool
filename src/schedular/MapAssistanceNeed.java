package schedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import request.Location;
import request.ProvideAssistance;
import request.Request;
import request.SimpleRequest;
import route.Utility;
import vehicle.Car;

public class MapAssistanceNeed implements MappingStrategy {

	@Override
	public HashMap<String, Queue> MapDriverAndRequest(Queue reqQueue, Queue driverQueue, String driverName) {

		Queue reqTempQueue = new LinkedList();
		Queue driverQueue1 = new LinkedList();
		Driver driver1 = null, driver = null;
		MySQLAccess db = new MySQLAccess();
		HashMap hm = new HashMap<>();

		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		ListIterator<Driver> driverIterator = (ListIterator<Driver>) driverQueue.iterator();

		// Get first request's destination
		SimpleRequest reqTemp = (SimpleRequest) reqQueue.element();
		Location commonDestination = reqTemp.getDestination();
		SimpleRequest req;
		int driverCount = 0;

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

		driverIterator = (ListIterator<Driver>) driverQueue.iterator();

		while (driverIterator.hasNext() && (driverCount == 1)) {

			driver = (Driver) driverIterator.next();
			if (driver.getReadytoAssist()) {
				driverQueue1.add(driver);
				driverCount++;
			}
		}

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
}
