package rules;
import DAO.MySQLAccess;
import DAO.*;
/**
 * 
 * @author hegde
 *
 */
public class PaymentRule {
	
	MySQLAccess mySQLAccess=new MySQLAccess();
	
	public double parkingPayment(){
		
		double pricePerHour=mySQLAccess.getCarFare();
		
		return pricePerHour;
	}
	
	public double carPayment(){
		
		double pricePerMile=mySQLAccess.getParkingFare();
		
		return pricePerMile;
	}	

}
