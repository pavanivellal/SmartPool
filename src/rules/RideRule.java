package rules;
import DAO.MySQLAccess;
/**
 * 
 * @author hegde
 *
 */
public class RideRule {

	MySQLAccess mySQLAccess=new MySQLAccess();
	public int getWaitingDuration(){
		
		int waitingDuration=mySQLAccess.getDuration();
		return waitingDuration;
	}
	
	public int getMaxNumOfCustomers(){
		
		int MaxNumOfCustomers=mySQLAccess.getMaxNumber();
		return MaxNumOfCustomers;
	}
}
