package schedular;

import java.util.Queue;

public interface MappingStrategy {	
	public Queue MapDriverAndRequest(Queue reqQueue);
}
