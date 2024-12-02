package AvailabilityDemand;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

/**
 * This is the class that processes input commands and collects and returns output notifications. 
 */
public class AvailabilityDemand {

	private String action;

	private String customerName;

	private String location;

	private Date startDate;

	private Date endDate;

	private String RentalCarorCustomerName;

	public List<RentalCarProvider>publishedCars=new ArrayList<>();

	//private Rental Car Provider[] rental Car Provider;

	private String customer;

	private Broker broker=new Broker();

	/**
	 * This method parses the input. Based on the input command, it will call the appropriate publisher or subscriber.
	 */
	public void processInput(String command)
	{
		String[] parts = command.split(",\\s*");//code for the parts
		action=parts[0].toLowerCase();
		if (parts.length > 1) {
			RentalCarorCustomerName= parts[1];
		}
		if (parts.length > 2) {
			location = parts[2];
		}
		if (parts.length > 3) {
			SimpleDateFormat start = new SimpleDateFormat("MM/dd/yyyy");

			try
			{
				this.startDate = start.parse(parts[3]);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		if (parts.length > 4) {
			//this.endDate = parseDate(parts[4]);
			SimpleDateFormat end = new SimpleDateFormat("MM/dd/yyyy");
			try
			{
				this.endDate = end.parse(parts[4]);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		if(action.equals("publish"))
		{

			RentalCarProvider newCar = new RentalCarProvider() {
				@Override
				public boolean publish(String providerName, String location, Date availableFrom, Date availableDate) {
					return super.publish(providerName, location, availableFrom, availableDate);
				}
			};

			broker.addPublisher(RentalCarorCustomerName,location,startDate,endDate);
			/*newCar.publish(RentalCarProviderName,location,startDate,endDate);
			publishedCars.add(newCar);*/
		}
		else if(action.equals("subscribe"));
		{
			customer1 customer = new customer1() {
				@Override
				public boolean subscribe(String name, String location, Date from, Date to) {
					return super.subscribe(name, location, from, to);
				}
			};

			broker.addSubscriber(RentalCarorCustomerName,location,startDate,endDate);

		}

	}

	/**
	 * This method is responsible for returning the consolidated notifications when the getAggregatedOutput( ) is called.
	 */
	public List<String> getAggregatedOutput() {

		/*SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<String> check=new ArrayList<>();
		check.add(action);
		check.add(RentalCarProviderName);
		check.add(location);
		check.add(sdf.format(startDate));
		check.add(sdf.format(endDate));
		return check;*/



		return null;
	}

	/**
	 * This method is responsible for removing all the stored published and subscribed events when the getAggregatedOutput( ) method is called.
	 */
	public void reset() {

	}

}
