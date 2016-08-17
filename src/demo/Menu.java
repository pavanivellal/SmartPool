package demo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.Queue;

import org.omg.CORBA.Request;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;

import membership.LicenseDetails;
import membership.MemberShip;
import payment.CardPayment;
import report.ReportSetup;
import request.ProvideAssistance;
import request.SimpleRequest;
import schedular.Schedular;
import vehicle.Car;
import vehicle.Vehicle;

public class Menu {

	private Customer customer = new Customer();
	private AdminMenu adminMenu = new AdminMenu();
	private DriverMenu driverMenu = new DriverMenu();
	private CustomerMenu customerMenu = new CustomerMenu();
	private Driver driver = new Driver();
	Request req;
	private Queue reqQueue = new LinkedList();

	public Menu() {
		while (true)
			displayMainMenu();
	}
/*
 * Created by shital
 */
	private void displayMainMenu() {
		// This is the main menu for the system
		System.out.println();
		System.out.println("--------Welcome to SmartPool sysetm--------");
		System.out.println("-------------------------------------------");
		System.out.println("1. Customer Menu");
		System.out.println("2. Driver Menu");
		System.out.println("3. Admin Menu");
		System.out.println("0. Exit system");
		System.out.println("Enter menu option : ");
		try {
			String answer = Client.getReader().readLine();
			if (answer.isEmpty() || answer.length() >= 2) {
				System.out.println("Input is wrong. Please select again.");
				displayMainMenu();
			} else {
				int input = Integer.parseInt(answer);
				switch (input) {
				case 0:
					System.out.println("Successfully exited the system");
					System.exit(0);
					break;
				case 1:
					displayCustomerMenu();
					break;
				case 2:
					displayDriverMenu();
					break;
				case 3:
					displayAdminMenu();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayCustomerMenu() {

		try {
			while (true) {
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.println("Welcome Customer");
				System.out.println("1. Register");
				System.out.println("2. View account");
				System.out.println("3. Update account");
				System.out.println("4. Delete account");
				System.out.println("5. Request a ride");
				System.out.println("6. Cancel ride");
				System.out.println("7. Bid for a ride");
				System.out.println("8. Give feedback for recent ride");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: ");
				String line = Client.getReader().readLine();
				if (line.isEmpty() || line.length() >= 2) {
					System.out.println("Input is wrong. Please select again.");
					displayMainMenu();
				} else {
					int option = Integer.parseInt(line);
					switch (option) {
					case 0:
						break;
					case 1:
						customer = customerMenu.registration();
						if (customer != null) {
							System.out.println(customer.toString());
						}
						break;
					case 2:
						customer = customerMenu.getCustomerByUserName();
						if (customer != null) {
							System.out.println(customer.toString());
						}
						else
							System.out.println("Wrong id! Try again.");
						break;
					case 3:
						customer = customerMenu.updateCustomer();
						if (customer != null) {
							System.out.println(customer.toString());
						}
						break;
					case 4:
						customerMenu.deleteCustomer();
						break;
					case 5:
						System.out.println("\nDear Customer, Please enter request details:"); 
						   requestRide();	
						break;
					case 6:
						break;
					case 7:

						break;

					case 8:
						customerMenu.addCustomerFeedback();
						break;
					}
					if (option == 0) {
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/*
 * 
 */
	private void displayDriverMenu() {
		try {
			while (true) {
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.println("Welcome Driver");
				System.out.println("1. Register");
				System.out.println("2. View account");
				System.out.println("3. Update account");
				System.out.println("4. Delete account");
				System.out.println("5. Update Car");
				System.out.println("6. Check for ride requests");
				System.out.println("7. Book parking");
				System.out.println("8. Give feedback for customer");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: ");
				String line = Client.getReader().readLine();
				if (line.isEmpty() || line.length() >= 2) {
					System.out.println("Input is wrong. Please select again.");
					displayMainMenu();
				} else {
					int option = Integer.parseInt(line);
					switch (option) {
					case 0:
						break;
					case 1:
						driver = driverMenu.registration();
						if (driver != null) {
							System.out.println(driver.toString());
						}
						break;
					case 2:
						driver = driverMenu.getDriverByUserName();
						if (driver != null) {
							System.out.println(driver.toString());
						}
						else
							System.out.println("Wrong id! Try again.");
						break;
					case 3:
						driver = driverMenu.updateDriverCard();
						if (driver != null) {
							System.out.println(driver.toString());
						}
						break;
					case 4:
						driverMenu.deleteDriver();
						break;
					case 5:
						driver = driverMenu.updateDriverCar();
						if (driver != null) {
							System.out.println(driver.toString());
						}
						break;
					case 6:
                            System.out.println("Enter your user name : ");
                            String inputName = Client.getReader().readLine();
                            Schedular sc = new Schedular(reqQueue,inputName);
                            sc.scheduleRide();
						break;
					case 7:

						break;	
					case 8:
						driverMenu.addDriverFeedback();
						break;					
						
					}
					if (option == 0) {
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayAdminMenu() {
		try {
			while (true) {
				adminMenu = new AdminMenu();
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.println("Welcome Admin");
				System.out.println("1. List of Customers");
				System.out.println("2. List of Drivers");
				System.out.println("3. Generate Reports");
				System.out.println("4. Update Pricing Rules");
                System.out.println("5. Notify Offers to Customers");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: ");
				String line = Client.getReader().readLine();
				if (line.isEmpty() || line.length() >= 2) {
					System.out.println("Input is wrong. Please select again.");
					displayMainMenu();
				} else {
					int option = Integer.parseInt(line);
					switch (option) {
					case 0:
						break;
					case 1:
						adminMenu.getListOfCustomers();
						break;
					case 2:
						adminMenu.getListOfDrivers();
						break;
					case 3:
						adminMenu.generateReports();
						break;                     
                    case 4:
                        break;
					case 5:
						adminMenu.NotifyOffers();
						break;
					}
					if (option == 0) {
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void requestRide(){
        
        SimpleRequest req = new SimpleRequest();
        req.acceptUserName();
        req.acceptSource();
        req.acceptDestination();
        req.acceptDateAndTime();
        req.acceptCarType();
        ProvideAssistance assistance = new ProvideAssistance(req);
        assistance.checkAssistaceNeeded();
        reqQueue.add(req);		
    }
}
