package AvailabilityDemand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Broker {

	private List<String> subscribers;
	public List<RentPeriod> carPublishers=new ArrayList<>();

	//private Rental Car Provider rental Car Provider;

	private String customer;

	private AvailabilityDemand availabilityDemand;

	public void addPublisher(String RentalCarProviderName, String location, Date startDate, Date endDate)
	{
		RentPeriod carRentPeriod = new RentPeriod(RentalCarProviderName,location,startDate,endDate);
		RentPeriod carExists = checkCar(carRentPeriod);
		//System.out.println("comes to broker class1");

		if(carExists==null)
		{
			carPublishers.add(carRentPeriod);
			//System.out.println("this comes to null");
		}
		else
		{
			//System.out.println("this comes to bulibate");
		}

	}

	public RentPeriod checkCar(RentPeriod carRental)
	{
		if(carPublishers.size()==0)
		{

			return null;
		}

		for(RentPeriod temp:carPublishers)
		{

			if((temp.getRentalName().equals(carRental.getRentalName()))&&(temp.getLocation().equals(carRental.getLocation()))&&(temp.getStartDate().equals(carRental.getStartDate()))&&(temp.getEndDate().equals(carRental.getEndDate())))
			{
				return carRental;
			}
		}
		return null;
	}

	public Map<Car, RentPeriod> getCar() {
		return null;
	}

	public void removeSubscriber() {

	}

	public void addSubscriber(String CustomerName, String location, Date startDate, Date endDate)
	{


	}

	public void Notify() {

	}

}
