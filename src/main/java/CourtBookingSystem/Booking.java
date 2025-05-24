package CourtBookingSystem;


import java.time.LocalDateTime;
public class Booking {

    private int booking_id;
    private Facility facility;
    private LocalDateTime dateTime;
    private int duration;
    private float total_price;

    public Booking() {

    }

    public Booking(int booking_id, Facility facility, LocalDateTime dateTime, int duration) {
        this.booking_id = booking_id;
        this.facility = facility;
        this.dateTime = dateTime;
        this.duration = duration;
        this.total_price = facility.getPricePerHour() * duration;
    }

    public void makeBooking() {
        System.out.println("Booking " + booking_id + " created.");
    }

    public void cancelBooking() {
        System.out.println("Booking " + booking_id + " canceled.");
    }

    public int getBookingId() {
        return booking_id;
    }

    public Facility getFacility() {
        return facility;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public float getTotalPrice() {
        return total_price;
    }

}
