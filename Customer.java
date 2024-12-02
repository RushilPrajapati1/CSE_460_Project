package AvailabilityDemand;
import java.util.Date;
abstract class customer1 implements ISubscriber{
    private AvailabilityDemand availabilityDemand;

    private Broker[] broker;

    private Date startDate;

    private Date endDate;

    private String customerName;

    private String location;

   /* public subscriber(String customerName, String location,Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerName=customerName;
        this.location=location;
        //this.providerName = providerName;
    }*/
    public boolean subscribe(String name, String location, Date from, Date to) {
        return false;
    }

    public boolean unSubscribe(String location, Date from, Date to) {
        return false;
    }


    //*
    // * @see AvailabilityDemand.ISubscriber#subscribe(java.lang.String, java.lang.Date, java.lang.Date)
    public boolean subscribe(String location, Date from, Date to) {
        return false;
    }

}
