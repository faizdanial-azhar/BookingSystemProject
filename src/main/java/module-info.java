module com.example.bookingreal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bookingreal to javafx.fxml;
    exports com.example.bookingreal;
    exports CourtBookingSystem;
    opens CourtBookingSystem to javafx.fxml;
    exports PaymentPackage;
    opens PaymentPackage to javafx.fxml;
    exports StudentAuthGUI;
    opens StudentAuthGUI to javafx.fxml;
    exports FacilityBooking;
    opens FacilityBooking to javafx.fxml;
}
