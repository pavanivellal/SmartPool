package request;

public class ProvideAssistance extends RequestDecorator {

	public ProvideAssistance(Request request) {
		super(request);
	}

	public void checkAssistaceNeeded() {		
		System.out.println("Do you need an assistance? ( Y / N ) ");
		input = keyboard.nextLine();
        if(input.equalsIgnoreCase("y")){
        	provideNeededAssistance();
        }
	}
	public void provideNeededAssistance() {
		/*System.out.println("Do you need assistance because of physical disabilities? ( Y /N )");
		input = keyboard.nextLine();	*/
		request.setAssistanceNeed(true);
	}
	
	public void display() {				
		super.display();
	}
}
