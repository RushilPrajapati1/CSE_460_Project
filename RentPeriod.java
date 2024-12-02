package AvailabilityDemand;
import java.util.Date;
/**
 * This is the class that provides details about rental car's rent period (available from and to dates).
 */
public class RentPeriod {

	/**
	 * This is the attribute for start date of the rent period of data type Date.
	 */
	private Date startDate;

	private Date endDate;

	private String rentalName;

	private String location;

	private Car car;

	public RentPeriod(String rentalName, String location,Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentalName=rentalName;
		this.location=location;
		//this.providerName = providerName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getLocation() {
		return location;
	}

	public String getRentalName() {
		return rentalName;
	}

	public Date getStartDate() {
		return startDate;
	}
}
