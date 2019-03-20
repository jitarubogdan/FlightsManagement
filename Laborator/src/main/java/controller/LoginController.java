package controller;

import domain.Angajat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.AngajatService;

import javafx.scene.control.TextField;
import service.BiletService;
import service.ClientService;
import service.ZborService;

import java.io.IOException;

public class LoginController {

    private AngajatService srvA;
    private ZborService srvZ;
    private ClientService srvC;
    private BiletService srvB;

    @FXML
    TextField textFieldUser;

    @FXML
    TextField textFieldPassword;

    public void setService(AngajatService srvA, ZborService srvZ, ClientService srvC, BiletService srvB){
        this.srvA = srvA;
        this.srvZ = srvZ;
        this.srvC= srvC;
        this.srvB = srvB;
    }

    public void showFlightsWindow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/flightWindow.fxml"));
            AnchorPane rootLayout = loader.load();
            FlightController controller = loader.getController();

            controller.setService(srvZ,srvC,srvB);

            Stage primaryStageFlight = new Stage();
            Scene scene = new Scene(rootLayout);
            primaryStageFlight.setTitle("Flights");
            primaryStageFlight.setScene(scene);
            controller.setStage(primaryStageFlight);
            primaryStageFlight.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) {
        String user = textFieldUser.getText();
        String password = textFieldPassword.getText();
        textFieldPassword.setText("");
        Angajat result = srvA.isAngajatService(user,password);
        if(result == null){
            showErrorMessage("Id sau password invalide!");
        }
        else{
            showFlightsWindow();
        }
    }

    private void showErrorMessage(String s){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Eroare");
        message.setContentText(s);
        message.showAndWait();
    }
}
