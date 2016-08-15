package payment;

public abstract class Parking extends Payment {
	
	int id;
	int driver_id;
	int no_of_hours;
	String type;
	double prk_per_person;
	double parking_fee;
	double tot_per_hed;
	
	public Parking(CalculatePayment CalPmt, int no_of_memb) {
		super(CalPmt, no_of_memb);
	}
	
	public Payment payment;
	
	void cost_per_mile()	
	{
		this.cost_per_mile();
	}
	void parking_cost()
	{
		this.parking_cost();
	}
	double total_payment()
	{
		return this.total_payment();	
	}
	void make_payment()
	{
		this.make_payment();
	}
	public double getPrk_per_person() {
		return prk_per_person;
	}
	public void setPrk_per_person(double prk_per_person) {
		this.prk_per_person = prk_per_person;
	}
	public double getParking_fee() {
		return parking_fee;
	}
	public void setParking_fee(double parking_fee) {
		this.parking_fee = parking_fee;
	}
	public double getTot_per_hed() {
		return tot_per_hed;
	}
	public void setTot_per_hed(double tot_per_hed) {
		this.tot_per_hed = tot_per_hed;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	
	public int getNo_of_hours() {
		return no_of_hours;
	}
	public void setNo_of_hours(int no_of_hours) {
		this.no_of_hours = no_of_hours;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Parking details: " +"\n"+"Parking id: " + id + ", Driver_id: " + driver_id + ", Type: " + type +", Hourly rate: " + parking_fee + ", No of hours parked : "
				+ no_of_hours +  ", Total charge: "  + payment ;
	}

	
}
