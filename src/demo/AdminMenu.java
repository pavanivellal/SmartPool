package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import report.ReportSetup;
import specialOffer.CustomerGroup;
import specialOffer.DriverGroup;

public class AdminMenu {

	private BufferedReader reader;

	public AdminMenu() {
		reader = Client.getReader();
	}

	public void getListOfCustomers() {
		try {
			System.out.println("--------------------------------------------------------------");
			System.out.println("List of all customers: ");
			System.out.println("--------------------------------------------------------------");
			MySQLAccess da = new MySQLAccess();
			List<Customer> customerList = null;
			customerList = da.getAllCustomers();
			if (customerList != null) {
				for (Customer customer : customerList) {
					System.out.println();
					System.out.println(customer.toString());
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getListOfDrivers() {
		try {
			System.out.println("--------------------------------------------------------------");
			System.out.println("List of all drivers: ");
			System.out.println("--------------------------------------------------------------");
			MySQLAccess da = new MySQLAccess();
			Queue<Driver> driverQueue = null;
			driverQueue = da.getAllDrivers();
			if (driverQueue != null) {
				for (Driver driver : driverQueue) {
					System.out.println();
					System.out.println(driver.toString());
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateReports() {
		ReportSetup setup = new ReportSetup();
		setup.reportSetup();
	}

	public void NotifyOffers() {
		MySQLAccess MSA = new MySQLAccess();
		ArrayList<Customer> customer = MSA.getAllCustomers();
		ArrayList<Driver> driver =  (ArrayList<Driver>) MSA.getAllDrivers();
		String offertype = null;

		System.out.println("Select Offer to Broadcast:\n1.Customer Offers \n2.Driver Offers");
		try {
			offertype = reader.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (offertype) {
		case "1":
			CustomerGroup cg = new CustomerGroup("Customers", customer);
			break;
		case "2":
			DriverGroup dg = new DriverGroup();
			break;
		}
	}
}
