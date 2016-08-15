package feedback;

public class CustomerFeedback extends FeedBack {

	boolean wasCarClean;;

	
	public boolean wasCarClean() {
		return wasCarClean;
	}

	public void setWasCarClean(boolean wasCarClean) {
		this.wasCarClean = wasCarClean;
	}

	public CustomerFeedback(String comment, int rating, boolean wasCarClean) {
		setComment(comment);
		setRating(rating);
		setWasCarClean(wasCarClean);
	}

	@Override
	public void displayFeedback() {
		// TODO Auto-generated method stub
	}
}
