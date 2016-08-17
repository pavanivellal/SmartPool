package specialOffer;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Member;

import membership.Customer;

public class CustomerGroup implements UserGroup{
	
	ArrayList<Customer> customer;
	String name;
	Customer temp_cust;
	
	
	public CustomerGroup(String i_name, ArrayList<Customer> customer)
	{
		this.name = i_name;
		this.customer = customer;
		
		System.out.println("The following members are added to customer group :");
		
		for(int i = 0; i< customer.size(); i++)
		{
			temp_cust = customer.get(i);
			System.out.println(temp_cust.getFirstName() + " " + temp_cust.getLastName());
		}
	}

	@Override
	public void update() {
		System.out.println("Notify Observer Group " + name);
	}
}
