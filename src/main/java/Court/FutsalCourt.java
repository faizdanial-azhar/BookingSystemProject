package Court;

public class FutsalCourt extends Facility {
    public FutsalCourt(int facility_no) {
        super(facility_no, "Futsal Court", 10, 10.0f,
                java.time.LocalTime.of(8, 0), java.time.LocalTime.of(22, 0));
    }
}

