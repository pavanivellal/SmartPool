package payment;

public class ClosedParking extends Parking{
	
	double total;
	int no_of_memb;
	double charge_per_hr = 3;
	
	public ClosedParking(CalculatePayment CalPmt, int no_of_memb) {
		super(CalPmt, no_of_memb);
	}

	public void addCharges(double hours)
	{
		super.parking_fee = charge_per_hr * hours;
		CalPmt.tot_cost = super.parking_fee + CalPmt.tot_cost;
		System.out.println("Total Parking fee: " + super.parking_fee);
		super.prk_per_person = (super.parking_fee) / super.no_of_memb;
		System.out.println("Parking fee per person: " + super.prk_per_person);
		CalPmt.ind_cost = super.tot_per_hed = super.prk_per_person + CalPmt.ind_cost;
		System.out.println("Total amount per person with parking: " + CalPmt.ind_cost);
		//return Pmt.ind_cost;
	}

}