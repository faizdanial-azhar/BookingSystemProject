package BookingPackage;

import FacilityPackage.Facility;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import FacilityPackage.FacilityManager;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;


public class BookingInterfaces extends Booking {

    public BookingInterfaces(Facility selectedFacility) {
        super(selectedFacility);

    }


    public Stage bookingGateway() {
        Stage stage = new Stage();
        // Create Time Selection components
        Label timeLabel = new Label("Select Time:");
        TextField startTimeField = new TextField();
        startTimeField.setPromptText("Start Time (e.g., 14)");
        startTimeField.setPrefWidth(150);

        TextField endTimeField = new TextField();
        endTimeField.setPromptText("End Time (e.g., 16)");
        endTimeField.setPrefWidth(150);

        // Create UI components - Title and Facility Details
        Label titleLabel = new Label("Booking Details");
        Label facilityNoLabel = new Label("Facility No: " + Booking.getFacilityNo());
        Label priceLabel = new Label("Price/Hour: RM" + Booking.getPricePerHour());
        Label courtLabel = new Label("Court: " + Booking.getFacilityName());
        Label totalPriceLabel = new Label("Total Price: RM" + Booking.getTotalPrice());
        totalPriceLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 20px;");

        Label durationLabel = new Label("Duration: 0 hour(s)");


        // Add listeners to update duration when time fields change
        startTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
            Booking.setDuration(newValue, endTimeField.getText());
            durationLabel.setText("Duration: " + Booking.getDuration() + " hour(s)");
            totalPriceLabel.setText("Total Price: RM" + Booking.getTotalPrice());
        });

        endTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
            Booking.setDuration(startTimeField.getText(), newValue);
            durationLabel.setText("Duration: " + Booking.getDuration() + " hour(s)");
            totalPriceLabel.setText("Total Price: RM" + Booking.getTotalPrice());
        });

        // Create Date Selection components
        DatePicker datePicker = new DatePicker(LocalDate.now());
        Label dateLabel = new Label("Booking for: " + LocalDate.now().toString());

        // Set up date picker action
        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            dateLabel.setText("Booking for: " + selectedDate.toString());
        });



        //  Book Now button
        Button bookButton = new Button("Book Now");
        bookButton.setPrefWidth(100);


        // Set button action (placeholder for now)
        bookButton.setOnAction(e -> {
            // Update duration one more time to ensure it's correct
            Booking.setDuration(startTimeField.getText(), endTimeField.getText());
            durationLabel.setText("Duration: " + Booking.getDuration() + " hour(s)");

            System.out.println("Booking requested for: " + datePicker.getValue() +
                               " from " + startTimeField.getText() +
                               " to " + endTimeField.getText() +
                               " (Duration: " + Booking.getDuration() + " hour(s))");
            // Add actual booking logic here
        });

        // Create layout - Facility Details Box (Left)
        VBox detailsBox = new VBox(10); // 10px spacing between elements
        detailsBox.setStyle("-fx-padding: 15px;");
        detailsBox.getChildren().addAll(
                titleLabel,
                facilityNoLabel,
                priceLabel,
                courtLabel,
                durationLabel,totalPriceLabel
        );

        // Create layout - Date Selection Box (Top-Center)
        VBox dateBox = new VBox(10);
        dateBox.getChildren().addAll(datePicker, dateLabel);

        // Create layout - Time Selection Box (Center)
        VBox timeBox = new VBox(10);

        timeBox.getChildren().addAll(timeLabel, startTimeField, endTimeField);

        // Create layout - Button Box (Bottom-Center)
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(bookButton);

        // Combine Time and Button boxes
        VBox centerBox = new VBox(10);
        centerBox.getChildren().addAll(dateBox, timeBox, buttonBox);

        // Set up the main layout
        BorderPane rootPane = new BorderPane();
        rootPane.setLeft(detailsBox);
        rootPane.setCenter(centerBox);

        Scene scene = new Scene(rootPane, 500, 400);
        stage.setTitle("Booking");
        stage.setScene(scene);
        return stage;
    }

}
