package schedular;

import java.util.LinkedList;
import java.util.Queue;

public class MappingContext {

	private MappingStrategy mapSt;
	private Queue reqQueue = new LinkedList();
	
	public MappingStrategy setMappingStrategy(String str, Queue reqQueue) { 	
		
		if(str == "cartype"){
			mapSt = new MapVehicleType();
		} 
		else if(str == "location"){
			mapSt = new MapLocation();
		}	
		return mapSt;
	}
	
	public Queue MapDriverAndRequest(String str, Queue reqQueue){
		
		mapSt = setMappingStrategy(str,reqQueue);
		reqQueue = mapSt.MapDriverAndRequest(reqQueue);
		return reqQueue;
	}

}
