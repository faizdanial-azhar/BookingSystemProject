package Court;

public class BadmintonCourt extends Facility {
    public BadmintonCourt(int facility_no) {
        super(facility_no, "Badminton Court", 4, 15.0f,
                java.time.LocalTime.of(8, 0), java.time.LocalTime.of(22, 0));
    }
}