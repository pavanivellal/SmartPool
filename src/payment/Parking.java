package payment;

public class Parking {
	
	//int id;
	//int driver_id;
	String ParkType;
	double prk_per_person;
	double hours;
	int no_of_memb;
	double tot_cost;
	int rate_per_hr;
	double tot_prk_fee;
	
	public Parking(String ParkType, double hours, int no_of_memb, double tot_cost) 
	{
		this.ParkType = ParkType;
		this.hours = hours;
		this.no_of_memb =  no_of_memb;
		this.tot_cost = tot_cost;
	}
	
	
	public double getParkingVal() 
	{
		switch(ParkType)
		{
		case "Covered":
		case "P1":
			rate_per_hr = 4;
			break;
			
		case "Open":
		case "P2":
			rate_per_hr = 2;
			break;
			
		default:
			prk_per_person = 0;
			System.out.println("No Parking Fees");
			break;
				
		}
		
		tot_prk_fee = rate_per_hr * hours;
		prk_per_person = tot_prk_fee / no_of_memb;
		System.out.printf("Total Parking charge : " + tot_prk_fee + "\nIndividual Parking charge : " + prk_per_person + "\n");
		
		
		return prk_per_person;
		
	}
	
}
