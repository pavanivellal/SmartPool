package schedular;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import request.Location;
import request.Request;
import request.SimpleRequest;

public class MapLocation implements MappingStrategy{

	public Queue MapDriverAndRequest(Queue reqQueue){
			
		Queue reqTempQueue = new LinkedList();
		
		//Get first request's destination
		SimpleRequest req = (SimpleRequest) reqQueue.element();
		Location commonDestination = req.getDestination();
		
		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		//Compare it with every other request to find the match
		while(listIterator.hasNext()){
			req = (SimpleRequest) listIterator.next();
			if(req.getDestination().getX() == commonDestination.getX() &&
			   req.getDestination().getY() == commonDestination.getY() &&
			   req.getCarType() == "Sedan"){
					reqTempQueue.add(req);
				}					
		}
		
		int noOfReq = reqTempQueue.size() - 1;
		if(noOfReq == 1){		
			System.out.println(noOfReq  + " Customer Request is Matching with Driver " +  "");				
		}
		else
		{
			System.out.println(noOfReq + " Customers Requests are Matching with Driver " +  "");	
		}
		return reqTempQueue;			
	}	
	
	public Location findCommonSource(Queue reqQueue){
		
		Location srcLocation = ((SimpleRequest)reqQueue.remove()).getSource();
		return srcLocation;	
	}
}
