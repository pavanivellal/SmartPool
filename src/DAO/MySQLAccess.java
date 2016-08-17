package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import feedback.Complain;
import feedback.CustomerFeedback;
import feedback.DriverFeedback;
import feedback.Recommendation;
import membership.CardDetails;
import membership.Customer;
import membership.Driver;
import membership.LicenseDetails;
import parking.Parking;
import payment.CalculatePayment;
import ride.Ride;
import vehicle.Car;
import vehicle.Vehicle;

//Created by Shital
public class MySQLAccess {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/carpool";
	private static final String USER = "root";
	private static final String PASS = "root";

	Statement statement = null;
	PreparedStatement preparedStatement = null;

	/*--------Customer related methods------*/
	// Method to add new customer in the database
	public int addNewCustomer(Customer customer) {
		int cust_id = 0;
		int i = 0;

		String addCustomer = null;
		Connection dbConnection = null;

		CardDetails cardDetails = customer.getCardDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Database connection problem occured.");
			e.printStackTrace();
		}

		try {

			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			addCustomer = "INSERT INTO customer "
					+ "(fname,lname,username,email,phoneno,cardtype,cardnumber,expirymonth,expiryyear,cvv,type) "

					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = dbConnection.prepareStatement(addCustomer, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getUserName());
			preparedStatement.setString(4, customer.getEmailId());
			preparedStatement.setString(5, customer.getPhoneNumber());
			preparedStatement.setString(6, cardDetails.getCardType());
			preparedStatement.setString(7, cardDetails.getCardNumber());
			preparedStatement.setInt(8, cardDetails.getExpiryMonth());
			preparedStatement.setInt(9, cardDetails.getExpiryYear());
			preparedStatement.setInt(10, cardDetails.getCvv());
			preparedStatement.setString(11, customer.getCustomerType());

			i = preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			cust_id = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("Problem occured while creating new customer");
			e.printStackTrace();
		}

		finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cust_id;
	}

	// Method to get the customer details given the customer id
	public Customer getCustomerById(int customerId) {
		int custId = customerId;
		String getCustomerById = null;
		Connection dbConnection = null;
		Customer customer = new Customer();
		CardDetails cardDetails = new CardDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getCustomerById = "Select * from customer where id = ?";
			preparedStatement = dbConnection.prepareStatement(getCustomerById);

			preparedStatement.setInt(1, custId);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				customer.setMemberId(rs.getInt("id"));
				customer.setFirstName(rs.getString("fname"));
				customer.setLastName(rs.getString("lname"));
				customer.setUserName(rs.getString("username"));
				customer.setEmailId(rs.getString("email"));
				customer.setPhoneNumber(rs.getString("phoneno"));
				customer.setCustomerType(rs.getString("type"));
				cardDetails.setCardType(rs.getString("cardtype"));
				cardDetails.setCardNumber(rs.getString("cardnumber"));
				cardDetails.setExpiryMonth(rs.getInt("expirymonth"));
				cardDetails.setExpiryYear(rs.getInt("expiryyear"));
				cardDetails.setCvv(rs.getInt("cvv"));
				customer.setCardDetails(cardDetails);
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}

	// Method to get the customer details given the userName
	public Customer getCustomerByUserName(String userName) {
		String custUName = userName;
		String getCustomerByUserName = null;
		Connection dbConnection = null;
		Customer customer = new Customer();
		CardDetails cardDetails = new CardDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getCustomerByUserName = "Select * from customer where username = ?";
			preparedStatement = dbConnection.prepareStatement(getCustomerByUserName);

			preparedStatement.setString(1, custUName);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				customer.setMemberId(rs.getInt("id"));
				customer.setFirstName(rs.getString("fname"));
				customer.setLastName(rs.getString("lname"));
				customer.setUserName(rs.getString("username"));
				customer.setEmailId(rs.getString("email"));
				customer.setPhoneNumber(rs.getString("phoneno"));
				customer.setCustomerType(rs.getString("type"));
				cardDetails.setCardType(rs.getString("cardtype"));
				cardDetails.setCardNumber(rs.getString("cardnumber"));
				cardDetails.setExpiryMonth(rs.getInt("expirymonth"));
				cardDetails.setExpiryYear(rs.getInt("expiryyear"));
				cardDetails.setCvv(rs.getInt("cvv"));
				customer.setCardDetails(cardDetails);
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}

	// Method to update the customer using userName
	public void updateCustomer(Customer customer) {

		String customerUpdate = null;
		Connection dbConnection = null;
		CardDetails cardDetails = customer.getCardDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			customerUpdate = "Update customer SET cardtype=?, " + "cardnumber=?, " + "expirymonth=?, "
					+ "expiryyear=?, " + "cvv=? " + "where username=? ";

			preparedStatement = dbConnection.prepareStatement(customerUpdate);

			preparedStatement.setString(1, cardDetails.getCardType());

			preparedStatement.setString(2, cardDetails.getCardNumber());
			preparedStatement.setInt(3, cardDetails.getExpiryMonth());
			preparedStatement.setInt(4, cardDetails.getExpiryYear());
			preparedStatement.setInt(5, cardDetails.getCvv());
			preparedStatement.setString(6, customer.getUserName());

			int i = preparedStatement.executeUpdate();
		}

		catch (Exception e) {
			System.out.println("Error occured while updating customer");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Method to delete the customer details given the customer id
	public void deleteCustomer(Customer c) {

		String customer = null;
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			customer = "Delete from customer where username=?";

			preparedStatement = dbConnection.prepareStatement(customer);
			preparedStatement.setString(1, c.getUserName());
			preparedStatement.executeUpdate();
		}

		catch (Exception e) {
			System.out.println("Error in deleting customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Method to get all the customers
	public ArrayList<Customer> getAllCustomers() {
		String getAllCustomers = null;
		Connection dbConnection = null;

		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getAllCustomers = "Select * from customer";
			preparedStatement = dbConnection.prepareStatement(getAllCustomers);

			ResultSet rs = preparedStatement.executeQuery(getAllCustomers);
			while (rs.next()) {
				Customer customer = new Customer();
				CardDetails cardDetails = new CardDetails();
				customer.setMemberId(rs.getInt("id"));
				customer.setFirstName(rs.getString("fname"));
				customer.setLastName(rs.getString("lname"));
				customer.setUserName(rs.getString("username"));
				customer.setEmailId(rs.getString("email"));
				customer.setPhoneNumber(rs.getString("phoneno"));
				customer.setCustomerType(rs.getString("type"));
				cardDetails.setCardType(rs.getString("cardtype"));
				cardDetails.setCardNumber(rs.getString("cardnumber"));
				cardDetails.setExpiryMonth(rs.getInt("expirymonth"));
				cardDetails.setExpiryYear(rs.getInt("expiryyear"));
				cardDetails.setCvv(rs.getInt("cvv"));
				customer.setCardDetails(cardDetails);
				customerList.add(customer);
			}
		} catch (Exception e) {
			System.out.println("Error in retreiving Customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customerList;
	}

	// Method to add customer feedback about driver

	public void addCustomerFeedback(CustomerFeedback feedback, int cust_id, int driver_id,
			Recommendation customereRecommendation, Complain customerComplain) {
		String addCustFeedback = null;
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Database connection problem occured.");
			e.printStackTrace();
		}

		try {

			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			addCustFeedback = "INSERT INTO customer_feedback "
					+ "(cust_id,driver_id,comment,rating,was_car_clean,is_recommended,complain) "

					+ "VALUES(?,?,?,?,?,?,?)";
			preparedStatement = dbConnection.prepareStatement(addCustFeedback, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, cust_id);
			preparedStatement.setInt(2, driver_id);
			preparedStatement.setString(3, feedback.getComment());
			preparedStatement.setInt(4, feedback.getRating());
			preparedStatement.setBoolean(5, feedback.wasCarClean());
			preparedStatement.setBoolean(6, customereRecommendation.isRecommended());
			preparedStatement.setString(7, customerComplain.getComplain());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Problem occured while adding feedback");
			e.printStackTrace();
		}

		finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/*---------------Driver related methods------------*/
	// Method to add new driver in the database
	public int addNewDriver(Driver driver) {
		int driver_id = 0;
		int i = 0;

		String addDriver = null;
		Connection dbConnection = null;

		CardDetails cardDetails = driver.getCardDetails();
		Car car = (Car) driver.getVehicle();
		LicenseDetails licenseDetails = driver.getLicenseDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Database connection problem occured.");
			e.printStackTrace();
		}

		try {

			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			addDriver = "INSERT INTO driver "
					+ "(fname,lname,username,email,phoneno,cardtype,cardnumber,expirymonth,expiryyear,cvv,availability,"
					+ "licenseno,validuntil,licenseplate,color,model,carStatus,addX,addY) "

					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = dbConnection.prepareStatement(addDriver, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, driver.getFirstName());
			preparedStatement.setString(2, driver.getLastName());
			preparedStatement.setString(3, driver.getUserName());
			preparedStatement.setString(4, driver.getEmailId());
			preparedStatement.setString(5, driver.getPhoneNumber());
			preparedStatement.setString(6, cardDetails.getCardType());
			preparedStatement.setString(7, cardDetails.getCardNumber());
			preparedStatement.setInt(8, cardDetails.getExpiryMonth());
			preparedStatement.setInt(9, cardDetails.getExpiryYear());
			preparedStatement.setInt(10, cardDetails.getCvv());
			preparedStatement.setString(11, driver.getAvailability());
			preparedStatement.setString(12, licenseDetails.getLicenseNumber());
			preparedStatement.setString(13, licenseDetails.getValidUntil());
			preparedStatement.setString(14, car.getLicensePlate());
			preparedStatement.setString(15, car.getColor());
			preparedStatement.setString(16, car.getModel());
			preparedStatement.setBoolean(17, car.getCarStatus());
			preparedStatement.setInt(18, driver.getX());
			preparedStatement.setInt(19, driver.getY());

			i = preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			driver_id = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("Problem occured while creating new customer");
			e.printStackTrace();
		}

		finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return driver_id;
	}

	// Method to get the customer details given the customer id
	public Driver getDriverById(int driverId) {
		int custId = driverId;
		String getDriverById = null;
		Connection dbConnection = null;
		Driver driver = new Driver();
		CardDetails cardDetails = new CardDetails();
		Car car = new Car();
		LicenseDetails licenseDetails = new LicenseDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getDriverById = "Select * from driver where id = ?";
			preparedStatement = dbConnection.prepareStatement(getDriverById);

			preparedStatement.setInt(1, custId);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				driver.setMemberId(rs.getInt("id"));
				driver.setFirstName(rs.getString("fname"));
				driver.setLastName(rs.getString("lname"));
				driver.setUserName(rs.getString("username"));
				driver.setEmailId(rs.getString("email"));
				driver.setPhoneNumber(rs.getString("phoneno"));
				driver.setAvailability(rs.getString("availability"));
				cardDetails.setCardType(rs.getString("cardtype"));
				cardDetails.setCardNumber(rs.getString("cardnumber"));
				cardDetails.setExpiryMonth(rs.getInt("expirymonth"));
				cardDetails.setExpiryYear(rs.getInt("expiryyear"));
				cardDetails.setCvv(rs.getInt("cvv"));
				driver.setCardDetails(cardDetails);
				licenseDetails.setLicenseNumber(rs.getString("licenseno"));
				licenseDetails.setValidUntil(rs.getString("validuntil"));
				driver.setLicenseDetails(licenseDetails);
				car.setLicensePlate(rs.getString("licenseplate"));
				car.setColor(rs.getString("color"));
				car.setModel(rs.getString("model"));
				car.setCarStatus(rs.getBoolean("carStatus"));
				driver.setVehicle(car);
				driver.setX(rs.getInt("addX"));
				driver.setY(rs.getInt("addY"));
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return driver;
	}

	// Method to get driver information by userName
	public Driver getDriverByUserName(String userName) {
		String driverUName = userName;
		String getDriverByUserName = null;
		Connection dbConnection = null;
		Driver driver = new Driver();
		CardDetails cardDetails = new CardDetails();
		Car car = new Car();
		LicenseDetails licenseDetails = new LicenseDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getDriverByUserName = "Select * from driver where username = ?";
			preparedStatement = dbConnection.prepareStatement(getDriverByUserName);

			preparedStatement.setString(1, driverUName);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				driver.setMemberId(rs.getInt("id"));
				driver.setFirstName(rs.getString("fname"));
				driver.setLastName(rs.getString("lname"));
				driver.setUserName(rs.getString("username"));
				driver.setEmailId(rs.getString("email"));
				driver.setPhoneNumber(rs.getString("phoneno"));
				driver.setAvailability(rs.getString("availability"));
				cardDetails.setCardType(rs.getString("cardtype"));
				cardDetails.setCardNumber(rs.getString("cardnumber"));
				cardDetails.setExpiryMonth(rs.getInt("expirymonth"));
				cardDetails.setExpiryYear(rs.getInt("expiryyear"));
				cardDetails.setCvv(rs.getInt("cvv"));
				driver.setCardDetails(cardDetails);
				licenseDetails.setLicenseNumber(rs.getString("licenseno"));
				licenseDetails.setValidUntil(rs.getString("validuntil"));
				driver.setLicenseDetails(licenseDetails);
				car.setLicensePlate(rs.getString("licenseplate"));
				car.setColor(rs.getString("color"));
				car.setModel(rs.getString("model"));
				car.setCarStatus(rs.getBoolean("carStatus"));
				driver.setVehicle(car);
				driver.setX(rs.getInt("addX"));
				driver.setY(rs.getInt("addY"));
			}

		} catch (Exception e) {
			System.out.println("Error occured while getting customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return driver;
	}

	// Method to retrieve all the drivers
	public Queue getAllDrivers() {
		String getAllDrivers = null;
		Connection dbConnection = null;

		Queue driverQueue = new LinkedList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getAllDrivers = "Select * from driver";
			preparedStatement = dbConnection.prepareStatement(getAllDrivers);

			ResultSet rs = preparedStatement.executeQuery(getAllDrivers);
			while (rs.next()) {
				Driver driver = new Driver();
				CardDetails cardDetails = new CardDetails();
				LicenseDetails licenseDetails = new LicenseDetails();
				Car car = new Car();
				driver.setMemberId(rs.getInt("id"));
				driver.setFirstName(rs.getString("fname"));
				driver.setLastName(rs.getString("lname"));
				driver.setUserName(rs.getString("username"));
				driver.setEmailId(rs.getString("email"));
				driver.setPhoneNumber(rs.getString("phoneno"));
				driver.setAvailability(rs.getString("availability"));
				driver.setRating(rs.getInt("rating"));
				cardDetails.setCardType(rs.getString("cardtype"));
				cardDetails.setCardNumber(rs.getString("cardnumber"));
				cardDetails.setExpiryMonth(rs.getInt("expirymonth"));
				cardDetails.setExpiryYear(rs.getInt("expiryyear"));
				cardDetails.setCvv(rs.getInt("cvv"));
				driver.setCardDetails(cardDetails);
				licenseDetails.setLicenseNumber(rs.getString("licenseno"));
				licenseDetails.setValidUntil(rs.getString("validuntil"));
				driver.setLicenseDetails(licenseDetails);
				car.setLicensePlate(rs.getString("licenseplate"));
				car.setColor(rs.getString("color"));
				car.setModel(rs.getString("model"));
				car.setCarStatus(rs.getBoolean("carStatus"));
				driver.setVehicle(car);
				driver.setX(rs.getInt("addX"));
				driver.setY(rs.getInt("addY"));
				driverQueue.add(driver);
			}
		} catch (Exception e) {
			System.out.println("Error in retreiving Customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return driverQueue;
	}

	// Method to update the driver using userName
	public void updateDriverCard(Driver driver) {

		String driverUpdate = null;
		Connection dbConnection = null;
		CardDetails cardDetails = driver.getCardDetails();
		Car car = (Car) driver.getVehicle();
		LicenseDetails licenseDetails = driver.getLicenseDetails();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			driverUpdate = "Update driver SET cardtype=?, " + "cardnumber=?, " + "expirymonth=?, " + "expiryyear=?, "
					+ "cvv=? " + "where username=? ";

			preparedStatement = dbConnection.prepareStatement(driverUpdate);

			preparedStatement.setString(1, cardDetails.getCardType());

			preparedStatement.setString(2, cardDetails.getCardNumber());
			preparedStatement.setInt(3, cardDetails.getExpiryMonth());
			preparedStatement.setInt(4, cardDetails.getExpiryYear());
			preparedStatement.setInt(5, cardDetails.getCvv());
			preparedStatement.setString(6, driver.getUserName());

			int i = preparedStatement.executeUpdate();
		}

		catch (Exception e) {
			System.out.println("Error occured while updating customer");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateDriverVehicle(Driver driver) {

		String driverUpdate = null;
		Connection dbConnection = null;
		Car car = (Car) driver.getVehicle();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			driverUpdate = "Update driver SET licenseplate=?, " + "color=?, " + "model=?, " + "where username=? ";

			preparedStatement = dbConnection.prepareStatement(driverUpdate);

			preparedStatement.setString(1, car.getLicensePlate());
			preparedStatement.setString(2, car.getColor());
			preparedStatement.setString(3, car.getModel());
			preparedStatement.setString(4, driver.getUserName());

			int i = preparedStatement.executeUpdate();
		}

		catch (Exception e) {
			System.out.println("Error occured while updating customer");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Method to delete driver from the database
	public void deleteDriver(Driver d) {

		String driver = null;
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			driver = "Delete from driver where username=?";

			preparedStatement = dbConnection.prepareStatement(driver);
			preparedStatement.setString(1, d.getUserName());
			preparedStatement.executeUpdate();
		}

		catch (Exception e) {
			System.out.println("Error in deleting customer information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addDriverFeedback(DriverFeedback feedback, int cust_id, int driver_id,
			Recommendation driverRecommendation, Complain driverComplain) {

		String addDriverFeedback = null;
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Database connection problem occured.");
			e.printStackTrace();
		}

		try {

			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			addDriverFeedback = "INSERT INTO driver_feedback "
					+ "(cust_id,driver_id,comment,rating,was_on_time,is_recommended,complain) "

					+ "VALUES(?,?,?,?,?,?,?)";
			preparedStatement = dbConnection.prepareStatement(addDriverFeedback, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, cust_id);
			preparedStatement.setInt(2, driver_id);
			preparedStatement.setString(3, feedback.getComment());
			preparedStatement.setInt(4, feedback.getRating());
			preparedStatement.setBoolean(5, feedback.wasCustomerOnTime());
			preparedStatement.setBoolean(6, driverRecommendation.isRecommended());
			preparedStatement.setString(7, driverComplain.getComplain());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Problem occured while adding feedback");
			e.printStackTrace();
		}

		finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
    /*-----Methods to fetch rules from database----*/
	public double getParkingFare() {

		String getCustomerById = null;
		double price = 0;
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getCustomerById = "Select * from payment_rule where id = ?";
			preparedStatement = dbConnection.prepareStatement(getCustomerById);

			preparedStatement.setInt(1, 2);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				price = rs.getDouble("per_mile_fair");
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting parking fare information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return price;
	}

	public double getCarFare() {

		String getCustomerById = null;
		double price = 0;
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getCustomerById = "Select * from payment_rule where id = ?";
			preparedStatement = dbConnection.prepareStatement(getCustomerById);

			preparedStatement.setInt(1, 2);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				price = rs.getDouble("per_mile_fair");
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting fare information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return price;
	}

	public int getDuration() {

		String getCustomerById = null;
		int price = 0;
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getCustomerById = "Select * from ride_rule where id = ?";
			preparedStatement = dbConnection.prepareStatement(getCustomerById);

			preparedStatement.setInt(1, 2);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				price = rs.getInt("waiting_duration");
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting duration information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return price;
	}

	public int getMaxNumber() {

		String getCustomerById = null;
		int price = 0;
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Database connection problem occured.");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getCustomerById = "Select * from ride_rule where id = ?";
			preparedStatement = dbConnection.prepareStatement(getCustomerById);

			preparedStatement.setInt(1, 2);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				price = rs.getInt("max_no_of_customers");
			}
		} catch (Exception e) {
			System.out.println("Error occured while getting customer number information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return price;
	}
	
	  /*-----Methods to fetch all rides from database----*/
	
	public ArrayList<Ride> getAllRides() {
		String getAllRides = null;
		Connection dbConnection = null;
		Queue queue = new LinkedList<>();
		ArrayList<Ride> rideList = new ArrayList<Ride>();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getAllRides = "Select * from ride";
			preparedStatement = dbConnection.prepareStatement(getAllRides);

			ResultSet rs = preparedStatement.executeQuery(getAllRides);
			while (rs.next()) {
				Ride ride = new Ride(queue);
				Ride.setId(rs.getInt("id"));
				ride.setDriver_id(rs.getInt("driver_id"));
				int customer_ids[] = new int[3];
				customer_ids[0]=rs.getInt("cust1_id");
				customer_ids[1]=rs.getInt("cust2_id");
				customer_ids[2]=rs.getInt("cust3_id");
				ride.setCustomer_ids(customer_ids);
				ride.setStart_time(rs.getString("start_time"));
				ride.setEnd_time(rs.getString("end_time"));
				ride.setFare(rs.getDouble("fare"));
				ride.setStatus(rs.getString("status"));
				rideList.add(ride);
			}
		} catch (Exception e) {
			System.out.println("Error in retreiving ride information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rideList;
	}

	  /*-----Methods to fetch all parking records from database----*/
	public ArrayList<Parking> getAllParking() {
		String getAllParking = null;
		Connection dbConnection = null;

		ArrayList<Parking> parkingList = new ArrayList<Parking>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in connecting to the database");
			e1.printStackTrace();
		}

		try {
			dbConnection = DriverManager.getConnection(URL, USER, PASS);
			getAllParking = "Select * from parking";
			preparedStatement = dbConnection.prepareStatement(getAllParking);

			ResultSet rs = preparedStatement.executeQuery(getAllParking);
			while (rs.next()) {
				Parking parking = new Parking("",0,0,0);
				parking.setId(rs.getInt("id"));
				parking.setDriver_id(rs.getInt("driver-id"));	
				parking.setHours(rs.getInt("no_of_hours"));
				parking.setParkType(rs.getString("type"));
				parking.setRate_per_hr(rs.getInt("hourly_rate"));
				parking.setTot_prk_fee(rs.getDouble("total_payment"));	
				parkingList.add(parking);
			}
		} catch (Exception e) {
			System.out.println("Error in retreiving parking information by ID");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return parkingList;
	}
}
