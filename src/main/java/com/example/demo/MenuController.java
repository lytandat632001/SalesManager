package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button Customer;
    @FXML
    public  void ActionCustomer(ActionEvent event) throws IOException{
        Customer.getScene().getWindow().hide();
        Stage customer = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
        Scene scene = new Scene(root);
        customer.setResizable(false);
        customer.setScene(scene);
        customer.show();
    }

}
