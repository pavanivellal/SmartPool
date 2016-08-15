package demo;
/**
 * 
 */

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import DAO.MySQLAccess;
import feedback.Complain;
import feedback.CustomerFeedback;
import feedback.Recommendation;
import membership.CardDetails;
import membership.Customer;
import membership.Driver;
import membership.MemberShip;

public class CustomerMenu {

	private BufferedReader reader;

	public CustomerMenu() {
		reader = Client.getReader();
	}

	public Customer registration() {

		try {

			Customer customer = new Customer();
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Registration for customer");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter following details: ");

			System.out.println("First name: ");
			customer.setFirstName(reader.readLine());

			System.out.println("Last name: ");
			customer.setLastName(reader.readLine());

			System.out.println("Choose the user name: ");
			customer.setUserName(reader.readLine());

			System.out.println("Email address: ");
			customer.setEmailId(reader.readLine());

			System.out.println("Phone No: ");
			customer.setPhoneNumber(reader.readLine());

			System.out.println("Membership Plan [1. Basic/2. Premium]: ");
			int membershipType = Integer.parseInt(reader.readLine());
			if (membershipType == 2) {
				customer.setCustomerType("Premium");
			} else
				customer.setCustomerType("Basic");

			CardDetails customerCardDetails = new CardDetails();
			System.out.println("Enter card details: ");

			System.out.println("Select card type [1.VISA/ 2. MASTERCARD]: ");
			int type = Integer.parseInt(reader.readLine());
			if (type == 1) {
				customerCardDetails.setCardType("VISA");
			} else
				customerCardDetails.setCardType("MASTERCARD");

			System.out.println("Card Number: ");
			customerCardDetails.setCardNumber(reader.readLine());

			System.out.println("Expiration month: ");
			customerCardDetails.setExpiryMonth(Integer.parseInt(reader.readLine()));

			System.out.println("Expiration year: ");
			customerCardDetails.setExpiryYear(Integer.parseInt(reader.readLine()));

			System.out.println("CVV: ");
			customerCardDetails.setCvv(Integer.parseInt(reader.readLine()));

			customer.setCardDetails(customerCardDetails);

			MemberShip memberShip = new MemberShip();
			memberShip.receiveMembershipApplication();

			System.out.println("To complete the registration pay for membership [y/n] ?: ");

			if (reader.readLine().equalsIgnoreCase("y")) {
				if (membershipType == 2)
					System.out.println("$50 is debited from your card as a membership payment.");
				else
					System.out.println("$20 is debited from your card as a membership payment.");
			} else {
				System.out.println("Registration cancelled.");
				return null;
			}

			System.out.println("Submit the registration details? [y/n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {

				memberShip.qualifyApplicant(customer);

				MySQLAccess da = new MySQLAccess();
				int cust_id = da.addNewCustomer(customer);
				memberShip.confirmMember();
				System.out.println("Your customer id is: " + cust_id);
				return customer;

			} else {
				System.out.println("Registration cancelled.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Some problem occured while registration.");
		return null;
	}

	public Customer getCustomerById() {

		Customer customer = new Customer();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter customer id: ");
			int id = Integer.parseInt(reader.readLine());

			MySQLAccess da = new MySQLAccess();

			customer = da.getCustomerById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Customer getCustomerByUserName() {

		Customer customer = new Customer();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter user name: ");
			String userName = reader.readLine();

			MySQLAccess da = new MySQLAccess();

			customer = da.getCustomerByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public void addCustomerFeedback() {
		try {

			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Feedback for the recent ride");
			System.out.println("-----------------------------------------------------------------");
			
			System.out.println("Enter customer id: ");
			int cust_id = Integer.parseInt(reader.readLine());

			System.out.println("Enter driver id: ");
			int driver_id = Integer.parseInt(reader.readLine());

			System.out.println("Enter comment: ");
			String comment = reader.readLine();

			System.out.println("Enter rating(1-5): ");
			int rating = Integer.parseInt(reader.readLine());

			Boolean isCarClean;
			
			System.out.println("Was car clean? (y/n): ");
			String answer = (reader.readLine());
		
			if (answer.equalsIgnoreCase("y"))
				isCarClean = true;
			else
				isCarClean = false;
			
			CustomerFeedback customerFeedback = new CustomerFeedback(comment, rating, isCarClean);
			Recommendation customereRecommendation = new Recommendation(customerFeedback);
			Complain customerComplain = new Complain(customerFeedback);
			System.out.println("Want to add Recommendation/Complain [y/n] ?: ");
			if (reader.readLine().equalsIgnoreCase("y")) {
				System.out.println("1.	Recommendation ");
				System.out.println("2.	Complain ");
				int option = Integer.parseInt(reader.readLine());
				if (option == 1) {		
					customereRecommendation.setRecommended(true);
					customerComplain.setComplain("");
					System.out.println("Driver is recommended by you: ");
					System.out.println("Thank you for recommendation. ");
				}
				if (option == 2) {
					customereRecommendation.setRecommended(false);
					System.out.println("Enter the complain : ");
					customerComplain.setComplain(reader.readLine());
					System.out.println("Complain is added ");
					System.out.println("We will get back to you about this complain. ");
				}
				MySQLAccess da = new MySQLAccess();
				da.addCustomerFeedback(customerFeedback,cust_id,driver_id,customereRecommendation,customerComplain);
				System.out.println("Successfully submitted the feedback.");		
			} else {
				MySQLAccess da = new MySQLAccess();
				customereRecommendation.setRecommended(false);
				customerComplain.setComplain("");
				da.addCustomerFeedback(customerFeedback,cust_id,driver_id,customereRecommendation,customerComplain);	
				System.out.println("Successfully submitted the feedback.");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer() {

		Customer customer = new Customer();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter user name: ");
			String userName = reader.readLine();
			
			customer.setUserName(userName);
			MySQLAccess da = new MySQLAccess();

			da.deleteCustomer(customer);
			
			System.out.println("Customer is deleted successfully.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Customer updateCustomer() {
		Customer customer = new Customer();
		CardDetails customerCardDetails = new CardDetails();
		try {

			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Update form for customer: ");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Please enter following fields to update: ");
			System.out.println("Credit card details:  ");

			System.out.println("Select card type [1.VISA/ 2. MASTERCARD]: ");
			int type = Integer.parseInt(reader.readLine());
			if (type == 1) {
				customerCardDetails.setCardType("VISA");
			} else
				customerCardDetails.setCardType("MASTERCARD");

			System.out.println("Card Number: ");
			customerCardDetails.setCardNumber(reader.readLine());

			System.out.println("Expiration month: ");
			customerCardDetails.setExpiryMonth(Integer.parseInt(reader.readLine()));

			System.out.println("Expiration year: ");
			customerCardDetails.setExpiryYear(Integer.parseInt(reader.readLine()));

			System.out.println("CVV: ");
			customerCardDetails.setCvv(Integer.parseInt(reader.readLine()));

			customer.setCardDetails(customerCardDetails);

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {

				MySQLAccess da = new MySQLAccess();
				da.updateCustomer(customer);

				System.out.println("You are successfully Updated.");
				return customer;

			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Problem while updating customer.");
		return null;
	}

//	public Request requestACab() {
//
//		try {
//
//			Request request = new Request();
//			long time = System.currentTimeMillis();
//			request.setRequestId(time);
//			System.out.println();
//			System.out
//					.println("___________________________________________________________________");
//			System.out.println("Request a ride ");
//			System.out
//					.println("___________________________________________________________________");
//			System.out.println("Please enter details: ");
//
//			System.out.println("Do you want to ride in Taxi or a InstantCab: ");
//			request.setRequestType(reader.readLine());
//
//			HashMap<String, Location> locations = LocationMapping
//					.getLocationObject().getLocations();
//
//			int count = 1;
//
//			for (String string : locations.keySet()) {
//				System.out.println(count++ + ". " + string);
//			}
//
//			System.out.println("Pick Up Location [pick from above list]: ");
//			int index = Integer.parseInt(reader.readLine());
//			String selected = (String) (locations.keySet().toArray()[index - 1]);
//
//			Location location1 = locations.get(selected);
//
//			request.setPickX(location1.x);
//			request.setPickY(location1.y);
//			request.setPickUpLocation(selected);
//
//			System.out.println("Destination [pick from above list]:  ");
//			index = Integer.parseInt(reader.readLine());
//			selected = (String) (locations.keySet().toArray()[index - 1]);
//			Location location2 = locations.get(selected);
//
//			request.setDestX(location2.x);
//			request.setDestY(location2.y);
//			request.setDestination(selected);
//
//			Date date = null;
//			while (date == null) {
//				date = getInputBookingDate();
//				if (date == null) {
//					System.out
//							.println("Please enter the date in proper format.");
//				}
//			}
//
//			request.setBookingDate(date);
//
//			System.out.println("Enter Type [Basic/Luxury]: ");
//			request.setCarType(reader.readLine());
//
//			System.out.println("Do you want to bid for Cab [y/n]: ");
//			if (reader.readLine().equalsIgnoreCase("y")) {
//				Bid bid = new Bid();
//
//				System.out.println("Suggest Your Fare Amount of the ride: ");
//				bid.setFare(Double.parseDouble(reader.readLine()));
//
//				request.setBid(bid);
//			}
//
//			System.out.println("Do you want a Car Seat [y/n]: ");
//			if (reader.readLine().equalsIgnoreCase("y")) {
//				// DECORATOR PATTERN TO ADD CAR SEAT FEATURE
//
//				CarSeatDecorator decoratedRequest = new CarSeatDecorator(
//						request);
//				// request.setCarSeatFlag(true);
//			} else
//				request.setCarSeatFlag(false);
//
//			System.out.println("Do you want a pet friendly car [y/n]: ");
//			if (reader.readLine().equalsIgnoreCase("y")) {
//
//				PetFriendlyDecorator decoratedRequest = new PetFriendlyDecorator(
//						request);
//
//				// request.setPetFriendlyFlag(true);
//			} else
//				request.setPetFriendlyFlag(false);
//
//			double avg = calculateFare(request);
//			request.setFareEstimation(avg);
//
//			System.out.println("Do you want to submit a request? [y/n]: ");
//			if (reader.readLine().equalsIgnoreCase("y")) {
//				System.out
//						.println("Your Request has been created. Please wait for connecting to drivers.");
//
//				System.out.println(request.toString());
//				return request;
//
//			} else {
//				System.out
//						.println("You cancelled the request. Please try again");
//				return null;
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Your request is not complete. Please try again.");
//		return null;
//
//	}
}
