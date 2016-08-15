package specialOffer;

import java.util.ArrayList;

import membership.Customer;

public class Discount25 extends Offers{
	
	@Override
	public void notifySubscriber(ArrayList<Customer> customer, String subject) {
		
		Customer temp_cust;
		
		if(customer.size() > 0)
		{
			System.out.println("25% Discount offer sent to the following members :");
			for(int i = 0; i<customer.size() ; i++)
			{
				temp_cust = null;
				temp_cust = customer.get(i);
				System.out.println(temp_cust.getFirstName() + " " + temp_cust.getLastName());
			}
			
			
		}
		
	}
	
}
