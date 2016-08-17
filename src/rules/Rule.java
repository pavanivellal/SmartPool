package rules;

public abstract class Rule {
		
	void retrieve(){		
		connectToDatabase();
		getRule1();
		getRule2();	
	}
	
	void connectToDatabase(){
		
		System.out.println("connecting to db");
	}
		
	abstract int getRule1();
	
	abstract int getRule2();
		
}
