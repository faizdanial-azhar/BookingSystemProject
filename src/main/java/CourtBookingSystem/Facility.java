package CourtBookingSystem;


import java.time.LocalTime;

public class Facility {
    protected int facility_no;
    protected String facility_name;
    protected int capacity;
    protected float pricePerHour;
    protected LocalTime openTime;
    protected LocalTime closeTime;
    protected boolean isAvailable = true;

    public Facility() {

    }

    public Facility(int facility_no, String facility_name, int capacity, float pricePerHour,
                    LocalTime openTime, LocalTime closeTime) {
        this.facility_no = facility_no;
        this.facility_name = facility_name;
        this.capacity = capacity;
        this.pricePerHour = pricePerHour;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public boolean checkAvailability() {
        return isAvailable;
    }

    public void updateFacilityDetails(String name, int capacity, float price) {
        this.facility_name = name;
        this.capacity = capacity;
        this.pricePerHour = price;
    }

    public int getFacilityNo() {
        return facility_no;
    }

    public String getFacilityName() {
        return facility_name;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }
}
