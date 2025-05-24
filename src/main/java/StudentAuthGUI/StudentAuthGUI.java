package StudentAuthGUI;

import FacilityBooking.BookingPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class StudentAuthGUI extends Application {
    private TextField regMatricField, regNameField, regEmailField, regPhoneField, regFacultyField;
    private PasswordField regPasswordField;

    private TextField loginMatricField;
    private PasswordField loginPasswordField;

    private StudentManager manager = new StudentManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Login & Registration");

        // Create a TabPane
        TabPane tabPane = new TabPane();

        // --- Register Tab ---
        Tab registerTab = new Tab("Register");
        registerTab.setClosable(false);

        GridPane registerGrid = new GridPane();
        registerGrid.setPadding(new Insets(10, 10, 10, 10));
        registerGrid.setVgap(8);
        registerGrid.setHgap(10);

        // Register fields
        regMatricField = new TextField();
        regPasswordField = new PasswordField();
        regNameField = new TextField();
        regEmailField = new TextField();
        regPhoneField = new TextField();
        regFacultyField = new TextField();
        Button registerBtn = new Button("Register");

        // Add components to grid
        registerGrid.add(new Label("Matric No:"), 0, 0);
        registerGrid.add(regMatricField, 1, 0);
        registerGrid.add(new Label("Password:"), 0, 1);
        registerGrid.add(regPasswordField, 1, 1);
        registerGrid.add(new Label("Name:"), 0, 2);
        registerGrid.add(regNameField, 1, 2);
        registerGrid.add(new Label("Email:"), 0, 3);
        registerGrid.add(regEmailField, 1, 3);
        registerGrid.add(new Label("Phone:"), 0, 4);
        registerGrid.add(regPhoneField, 1, 4);
        registerGrid.add(new Label("Faculty Name:"), 0, 5);
        registerGrid.add(regFacultyField, 1, 5);
        registerGrid.add(registerBtn, 1, 6);

        registerTab.setContent(registerGrid);

        // --- Login Tab ---
        Tab loginTab = new Tab("Login");
        loginTab.setClosable(false);

        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(10, 10, 10, 10));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);

        // Login fields
        loginMatricField = new TextField();
        loginPasswordField = new PasswordField();
        Button loginBtn = new Button("Login");

        // Add components to grid
        loginGrid.add(new Label("Matric No:"), 0, 0);
        loginGrid.add(loginMatricField, 1, 0);
        loginGrid.add(new Label("Password:"), 0, 1);
        loginGrid.add(loginPasswordField, 1, 1);
        loginGrid.add(loginBtn, 1, 2);

        loginTab.setContent(loginGrid);

        // Add tabs to tabPane
        tabPane.getTabs().addAll(registerTab, loginTab);

        // Create the main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(tabPane);

        // Create scene
        Scene scene = new Scene(mainLayout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Register button action
        registerBtn.setOnAction(e -> {
            String matric = regMatricField.getText().trim();
            String password = regPasswordField.getText().trim();
            String name = regNameField.getText().trim();
            String email = regEmailField.getText().trim();
            String phone = regPhoneField.getText().trim();
            String faculty = regFacultyField.getText().trim();

            if (matric.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText(null);
                alert.setContentText("Matric, Password, and Name are required.");
                alert.showAndWait();
                return;
            }

            Student student = new Student(matric, password, name, email, phone, faculty);
            if (manager.registerStudent(student)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Success");
                alert.setHeaderText(null);
                alert.setContentText("Registration successful!");
                alert.showAndWait();
                clearRegisterFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText(null);
                alert.setContentText("Registration failed.");
                alert.showAndWait();
            }
        });

        // Login button action
        loginBtn.setOnAction(e -> {
            String matric = loginMatricField.getText().trim();
            String password = loginPasswordField.getText().trim();

            Student student = manager.loginStudent(matric, password);
            if (student != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Success");
                alert.setHeaderText(null);
                alert.setContentText("Login successful! Welcome, " + student.getName());
                alert.showAndWait();

                // Open booking page
                try {
                    // Close this window
                    primaryStage.close();

                    // Create and show the JavaFX BookingPage
                    BookingPage bookingPage = new BookingPage(student.getName());
                    bookingPage.showBookingPage(student.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid credentials.");
                alert.showAndWait();
            }
        });
    }

    private void clearRegisterFields() {
        regMatricField.setText("");
        regPasswordField.setText("");
        regNameField.setText("");
        regEmailField.setText("");
        regPhoneField.setText("");
        regFacultyField.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
