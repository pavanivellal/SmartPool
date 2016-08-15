package report;

public class ParkingReport extends Report {

	public ParkingReport(String reportDetails) {
		this();
		this.reportDetails = reportDetails;
	}

	public ParkingReport() {
		reportName = "Parking Report";
	}

	@Override
	public void displayReport() {
		super.displayReport();
	}

}
