package feedback;

public class Recommendation extends FeedBackDecorator {
	
	boolean isRecommended;
	
	public Recommendation(FeedBack feedback) {
		super(feedback);
	}

	public boolean isRecommended() {
		return isRecommended;
	}

	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	@Override
	public void displayFeedback() {
		super.displayFeedback();
	}
}
