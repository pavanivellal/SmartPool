package request;

import java.util.Scanner;

public abstract class Request {	

	public static Integer reqId = 0;
	Scanner keyboard = new Scanner( System.in );
	String input = "";
	String[] numbersStr;
	boolean _isAssistanceNeeded = false;
	
	public Request() {
		 reqId++;
	}

	public int getID(){
		return reqId;
	}

	public abstract void display();
	
	public void setAssistanceNeed(boolean isAssistanceNeeded){
		this._isAssistanceNeeded = isAssistanceNeeded;
	}
	
	public boolean getAssistanceNeed(){
		return this._isAssistanceNeeded;
	}
}
