package request;

public class AddVehicleType extends RequestDecorator{

	public AddVehicleType(Request req) {
		super(req);
	}
	
	public void display() {				
		super.display();
	}
	
}
