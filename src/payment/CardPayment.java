package payment;

import java.util.Scanner;

public class CardPayment extends Payment {

	double amount;
	int no_of_memb;
	Scanner scan = new Scanner(System.in);

	public CardPayment(CalculatePayment calPay1, int no_of_memb) {
		super(calPay1, no_of_memb);

	}

	@Override
	double total_payment() {

		amount = super.distribute(no_of_memb);
		return amount;
	}

	@Override
	public void make_payment() {
		System.out.println("Payment Made by Card");
	}

	@Override
	public void custMembershipPay(int memType) {
		if (memType == 2) {
			System.out.println("$50 is debited from your card as a membership payment.");
		} else
			System.out.println("$20 is debited from your card as a membership payment.");
	}

	@Override
	public void driverMembershipPay() {
		System.out.println("$50 is debited from your card as a membership payment.");
	}

}
