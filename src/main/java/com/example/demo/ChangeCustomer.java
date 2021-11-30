package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChangeCustomer {
    @FXML
    private TextField FullName;
    @FXML
    private RadioButton Male;
    @FXML
    private RadioButton Female;
    @FXML
    private TextField Date;
    @FXML
    private TextField Address;
    @FXML
    private TextField Phone;
    @FXML
    private TextField CreationDate;
    public void SetCustomer(Customer customer){
        FullName.setText(customer.getFullName());
        if(customer.getSex().equalsIgnoreCase("nam")){
            Male.setSelected(true);
            Female.setSelected(false);
        }
        else{
            Male.setSelected(false);
            Female.setSelected(true);
        }
        Date.setText(String.valueOf(customer.getDateOfBirth()));
        Address.setText(customer.getAddress());
        Phone.setText(customer.getPhoneNumber());
        CreationDate.setText(String.valueOf(customer.getCreationDate()));
    }
    public void ActionAccept(ActionEvent actionEvent) throws IOException {

    }
}
