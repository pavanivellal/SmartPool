package specialOffer;
/* Driver Offers
 * @author = Pavani Vellal
 * Driver Gets bonus on each ride
*/

import java.util.ArrayList;
import java.util.Queue;

import com.mysql.jdbc.Driver;

import membership.Customer;

public class DriverOffers extends Offers{
	
	//Bonus Percentage
	double bonus_p;
	Queue<membership.Driver> observer;
	membership.Driver temp_driver;

	public void notifySubscriber(String subject) {
		
		subject = subject + "%";
		System.out.println(subject + "\nNotification Sent to:");
		
		for(int i = 0; i< observer.size(); i++)
		{
			temp_driver = observer.remove();
			System.out.println(temp_driver.getFirstName() + " " + temp_driver.getLastName());
		}
		
		
	}

	
	public void addObservre(DriverGroup dg) {
		
		System.out.println("Driver Group Added to Driver Offers");	
		if(observer == null)
		{
			observer = dg.driver;
		}
		else
		{
			observer.addAll(dg.driver);
		}		
	}

	public void removeObserver(DriverGroup dg) {
		
		System.out.println("Driver Group Removed from Driver Offers");	
		if(observer == null)
		{
			System.out.println("No observers available");
		}
		else
		{
			observer.removeAll(dg.driver);
			
		}
		
	}
	
	public double getBonus()
	{
		return bonus_p;
		
	}
	
	public void setBonus(double bonus_p)
	{
		this.bonus_p = bonus_p;
		notifySubscriber("Bonus percentage updated to: " + bonus_p);
		
	}

}
