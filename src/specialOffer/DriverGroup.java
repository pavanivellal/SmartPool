package specialOffer;

import java.util.Queue;

import com.mysql.jdbc.Driver;

public class DriverGroup implements UserGroup{
	
	Queue<membership.Driver> driver;
	String name;
	membership.Driver temp_driver;
	
	public DriverGroup(String name, Queue<membership.Driver> driver2)
	{
		this.name = name;
		this.driver = driver2;
		
		System.out.println("The following members are added to Driver group :");
		
		for(int i = 0; i< driver.size(); i++)
		{
			temp_driver = driver.remove();
			System.out.println(temp_driver.getFirstName() + " " + temp_driver.getLastName());
		}
	}
	
	
	@Override
	public void update() {
		System.out.println("Notify Observer Group " + name);
	}
	


}
