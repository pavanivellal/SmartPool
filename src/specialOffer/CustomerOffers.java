package specialOffer;
/* Customer Offers
 * @author = Pavani Vellal
 * Customer gets discount on each ride
*/

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Member;

import membership.Customer;

public class CustomerOffers extends Offers {

	// Discount Percentage
	double discount_p;
	ArrayList<Customer> observer;
	Customer temp_cust;

	public void notifySubscriber(String subject) {

		subject = subject + "%";
		System.out.println(subject + "\nNotification sent to:");
		for (int i = 0; i < observer.size(); i++) {
			temp_cust = observer.get(i);
			System.out.println(temp_cust.getFirstName() + " " + temp_cust.getLastName());
		}
	}

	public void addObservre(CustomerGroup cg) {
		System.out.println("Customer Group Added to customer offers");
		if (observer == null) {
			observer = cg.customer;
		} else {
			observer.addAll(cg.customer);
		}
	}

	public void removeObserver(CustomerGroup cg) {

		System.out.println("Customer Group Removed from customer offers");
		if (observer == null) {
			System.out.println("No observers available");
		} else {
			observer.removeAll(cg.customer);

		}
	}

	public double getDiscount() {
		return discount_p;

	}

	public void setDiscount(double discount_p) {
		this.discount_p = discount_p;
		notifySubscriber("Discount percentage updated to: " + discount_p);
	}
}
