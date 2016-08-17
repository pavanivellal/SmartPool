package request;

public abstract class RequestDecorator extends Request {


	protected Request request;
	
	public RequestDecorator(Request request){
		this.request = request;
	}
	
	@Override
	public void display() {				
		this.request.display();
	}

}
