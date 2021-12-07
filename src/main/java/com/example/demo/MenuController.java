package com.example.demo;

import javafx.application.Platform;
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
    private Button Account;
    @FXML
    private Button LogOut;
    @FXML
    private Button Exit;
    @FXML
    public  void ActionCustomer(ActionEvent event) throws IOException{
        Customer.getScene().getWindow().hide();
        Stage customer = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
        Scene scene = new Scene(root);
        customer.setTitle("GoodFriend");
        customer.setResizable(false);
        customer.setScene(scene);
        customer.show();
    }
    public  void ActionAccount(ActionEvent actionEvent)throws IOException{
        Account.getScene().getWindow().hide();
        Stage StageAccount = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ManageAccount.fxml"));
        Scene scene = new Scene(root);
        StageAccount.setTitle("GoodFriend");
        StageAccount.setResizable(false);
        StageAccount.setScene(scene);
        StageAccount.show();
    }
    public void ActionLogOut(ActionEvent actionEvent)throws IOException{
        LogOut.getScene().getWindow().hide();
        Stage StageLogin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        StageLogin.setTitle("GoodFriend");
        StageLogin.setResizable(false);
        StageLogin.setScene(scene);
        StageLogin.show();
    }
    public void ActionExit(ActionEvent actionEvent)throws IOException{
        Platform.exit();
    }

}
