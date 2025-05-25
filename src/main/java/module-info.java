module com.example.bookingreal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bookingreal to javafx.fxml;
    exports com.example.bookingreal;

    exports PaymentPackage;
    opens PaymentPackage to javafx.fxml;
    exports StudentAuthGUI;
    opens StudentAuthGUI to javafx.fxml;
    exports FacilityPackage;
    opens FacilityPackage to javafx.fxml;


}
