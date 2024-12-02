package AvailabilityDemand;
import java.util.Date;
/**
 * This is the interface that publishes rental car provider name, location, availableFrom and availableTo dates and returns boolean value.
 */
public interface IPublisher {

	/**
	 * This is the operation used for publishing rental car availability details such as provider name, location, available from and to dates.
	 */
	public boolean publish(String providerName, String location, Date availableFrom, Date availableDate);

}
