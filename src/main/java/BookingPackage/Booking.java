package BookingPackage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import FacilityPackage.Facility;
import javafx.scene.control.Label;

public class Booking {
    private static int facilityNo;
    private LocalDateTime dateTime;
    private static int pricePerHOur;
    private static int totalPrice;
    private static String facilityName;
    private static int duration = 0;

    public Booking() {

    }

    public Booking(Facility selectedFacility) {
        this.facilityNo = selectedFacility.getFacilityNo();
        this.facilityName = selectedFacility.getName();
        this.pricePerHOur = (int) selectedFacility.getPricePerHour();
        this.facilityName = selectedFacility.getName();

    }

    public static int getFacilityNo() {
        return facilityNo;
    }

    protected static String getPricePerHour() {
        return String.valueOf(pricePerHOur);
    }


    public static int getTotalPrice() {
        totalPrice = duration * pricePerHOur;
        return totalPrice;
    }

    public static String getFacilityName() {
        return facilityName;
    }

    public static void setDuration(String startTime, String endTime) {
        try {
            if (startTime == null || startTime.isEmpty() || endTime == null || endTime.isEmpty()) {
                duration = 0;
                return;
            }

            // Parse hours only
            int start = Integer.parseInt(startTime);
            int end = Integer.parseInt(endTime);

            // Validate hour values
            if (start < 0 || start > 23 || end < 0 || end > 23) {
                duration = 0;
                return;
            }

            // Calculate hours between start and end time
            if (end >= start) {
                duration = end - start;
            } else {
                // If end time is earlier than start time, assume it's the next day
                duration = (24 - start) + end;
            }
        } catch (Exception e) {
            // In case of parsing error, set duration to 0
            duration = 0;
        }
    }

    public static int getDuration() {
        return duration;
    }


}
