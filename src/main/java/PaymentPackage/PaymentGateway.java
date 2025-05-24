package PaymentPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class PaymentGateway  {

    public Stage qwerPaymentGateway() {
        Stage stage = new Stage();
        HBox root = new HBox();
        root.getChildren().add(new Label("Payment Gateway"));
        Scene scene = new Scene(root,1000,1000);
        stage.setTitle("Payment Gateway");
        stage.setScene(scene);
        return stage;
    }

}
