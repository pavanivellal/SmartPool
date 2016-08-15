package schedular;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import request.Request;
import request.SimpleRequest;

public class MapVehicleType implements MappingStrategy{

	
	@Override
	public Queue MapDriverAndRequest(Queue reqQueue) {
		
		Queue reqTempQueue = new LinkedList();
		SimpleRequest req;
		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();

		
		while(listIterator.hasNext()){
			req = (SimpleRequest) listIterator.next();
			if(req.getCarType() == "Sedan"){
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
}
