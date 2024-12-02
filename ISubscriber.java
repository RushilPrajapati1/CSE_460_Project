package AvailabilityDemand;
import java.util.Date;
/**
 * This is the interface that subscribes/unsubcribes to a location, availableFrom and availableTo dates and returns boolean value.
 */
public interface ISubscriber {

	/**
	 * This is the operation used for subscribing to a location, available from and to dates which returns a boolean value.
	 */
	public boolean subscribe(String location, Date from, Date to);

	/**
	 * This is the operation used for unsubscribing to a location, available from and to dates which returns a boolean value.
	 */
	public boolean unSubscribe(String location, Date from, Date to);

}
