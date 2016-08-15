package demo;

import java.io.BufferedReader;
import java.util.List;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;

public class StaffMenu {

	private BufferedReader reader;

	public StaffMenu() {
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
			Queue<Driver> driverList = null;
			driverList = da.getAllDrivers();
			if (driverList != null) {
				for (Driver driver : driverList) {
					System.out.println();
					System.out.println(driver.toString());
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
