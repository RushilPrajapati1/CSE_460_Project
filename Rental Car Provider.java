package AvailabilityDemand;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

abstract class RentalCarProvider implements IPublisher {

	private String providerName;

	private String location;

	private Date availableFrom;

	private Date availableDate;

	private Map<String, List<RentPeriod>> publishedMap;

	private List<RentPeriod>publishedCars=new ArrayList<>();
	private AvailabilityDemand availabilityDemand;

	private Car car;

	private Broker[] broker;

	public boolean publish(String providerName, String location, Date availableFrom, Date availableDate) {

		//System.out.println("publish is called");

		RentPeriod carRentPeriod = new RentPeriod(providerName,location,availableFrom,availableDate);

		RentPeriod carExists = checkCar(carRentPeriod);

		if(publishedCars.size()==0)
		{
			//System.out.println("comes to null");

			publishedCars.add(carRentPeriod);
			System.out.println(publishedCars.size());
		}
		else
		{
			//System.out.println("comes here");
			if(carExists==null)
			{
				publishedCars.add(carRentPeriod);
				//System.out.println("publish is called");

			}
			else
			{
				publishedCars.remove(carExists);
				publishedCars.add(carRentPeriod);
				System.out.println("publish is removed");
			}
		}



		return false;

	}

	public RentPeriod checkCar(RentPeriod carRental)
	{
		/*if(publishedCars==null)
		{
			return carRental;
		}*/

		for(RentPeriod temp:publishedCars)
		{
			if((temp.getRentalName().equals(carRental.getRentalName()))&&(temp.getLocation().equals(carRental.getLocation()))&&(temp.getStartDate().equals(carRental.getStartDate()))&&(temp.getEndDate().equals(carRental.getEndDate())))
			{
				return carRental;
			}
		}

		return null;
	}



	

	public Map<Car, RentPeriod> returnCar(String location, Date fromDate) {
		return null;
	}

}
