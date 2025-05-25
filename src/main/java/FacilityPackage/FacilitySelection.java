package FacilityPackage;

import BookingPackage.BookingInterfaces;
import PaymentPackage.PaymentGateway;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import BookingPackage.Booking;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class FacilitySelection extends Application {
    private FacilityManager facilityManager = new FacilityManager();
    private List<Facility> facilities;
    private Facility selectedFacility = null;
    private String studentName;
    private Stage primaryStage;
    private Label selectedCourtLabel = new Label("Selected Facility: None");

    public FacilitySelection() {
        // Default constructor required by JavaFX
    }

    public FacilitySelection(String studentName) {
        this.studentName = studentName;
        // Initialize in a separate method that can be called from start()
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Facility Booking - Welcome " + (studentName != null ? studentName : ""));

        // Initialize facilities
        initializeFacilities();

        // Create main layout
        BorderPane mainLayout = new BorderPane();

        // Title label
        Label titleLabel = new Label("ClickSport");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        titleBox.setStyle("-fx-background-color: lightgray;");
        mainLayout.setTop(titleBox);


        Label infoLabel = new Label("Select a facility to proceed to booking page.");

        // Scrollable facility list
        VBox rootPane = new VBox();

        VBox facilityListBox = new VBox(10);
        facilityListBox.setPadding(new Insets(10));

        for (Facility facility : facilities) {
            facilityListBox.getChildren().add(createFacilityCard(facility));
        }

        rootPane.getChildren().addAll(infoLabel, selectedCourtLabel, facilityListBox);
        mainLayout.setCenter(rootPane);

        // Booking button
        Button bookingButton = new Button("Proceed Booking");
        bookingButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: lightgreen;");
        bookingButton.setOnAction(e -> {
            if (selectedFacility != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Facility Selected");
                alert.setHeaderText(null);
                alert.setContentText("You selected: " + selectedFacility.getName()+"\nRedirecting to booking page......");

                alert.showAndWait();

                BookingInterfaces booking = new BookingInterfaces(selectedFacility);

                Stage bookingStage = booking.bookingGateway();
                bookingStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a facility first.");
                alert.showAndWait();
            }
        });

        HBox buttonBox = new HBox(bookingButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        mainLayout.setBottom(buttonBox);

        // Create scene
        Scene scene = new Scene(mainLayout, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void initializeFacilities() {
        facilityManager.initializeDefaultFacilities();
        facilities = facilityManager.loadFacilities();
    }

    private BorderPane createFacilityCard(Facility facility) {
        BorderPane cardPane = new BorderPane();
        cardPane.setPadding(new Insets(10));
        cardPane.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-width: 1;");

        // Image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        try {
            File imgFile = new File(facility.getImagePath());
            if (imgFile.exists()) {
                Image image = new Image(new FileInputStream(imgFile));
                imageView.setImage(image);
            } else {
                // Placeholder for missing image
                StackPane placeholder = new StackPane(new Label("No Image"));
                placeholder.setStyle("-fx-background-color: gray;");
                placeholder.setPrefSize(150, 100);
                cardPane.setLeft(placeholder);
            }
        } catch (FileNotFoundException e) {
            // Placeholder for error loading image
            StackPane placeholder = new StackPane(new Label("Error Loading Image"));
            placeholder.setStyle("-fx-background-color: gray;");
            placeholder.setPrefSize(150, 100);
            cardPane.setLeft(placeholder);
        }

        if (imageView.getImage() != null) {
            cardPane.setLeft(imageView);
        }

        // Facility info
        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(0, 0, 0, 10));
        infoBox.setStyle("-fx-background-color: #ADD8E6;"); // Light blue

        infoBox.getChildren().addAll(
            new Label("Facility No: " + facility.getFacilityNo()),
            new Label("Facility Name: " + facility.getName()),
            new Label("Price/Hour: RM" + facility.getPricePerHour()),
            new Label("Availability: " + (facility.isAvailable() ? "Available" : "Not Available"))
        );

        // Selection logic
        infoBox.setOnMouseClicked(e -> {
            selectedFacility = facility;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Facility Selected");
            alert.setHeaderText(null);
            alert.setContentText(facility.getName() + " selected!");
            alert.showAndWait();
            selectedCourtLabel.setText("Selected Facility: " + facility.getName());

        });

        // Hover effects
        infoBox.setOnMouseEntered(e -> 
            infoBox.setStyle("-fx-background-color: #87CEFA;")  // Lighter blue on hover
        );

        infoBox.setOnMouseExited(e -> 
            infoBox.setStyle("-fx-background-color: #ADD8E6;")  // Back to original color
        );

        cardPane.setCenter(infoBox);

        return cardPane;
    }

    // Method to show the booking page from another class
    public void showBookingPage(String studentName) {
        this.studentName = studentName;
        if (primaryStage == null) {
            // If not already started, start a new JavaFX application
            Stage stage = new Stage();
            start(stage);
        } else {
            // If already started, just show the stage
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
