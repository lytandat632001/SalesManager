package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;


public class ChangeCustomer {
    @FXML
    private TextField FullName;
    @FXML
    private RadioButton Male;
    @FXML
    private RadioButton Female;
    @FXML
    private TextField DateOfBirth;
    @FXML
    private TextField Address;
    @FXML
    private TextField Phone;
    @FXML
    private Button Save;
    @FXML
    private Button Back;
    @FXML
    private TextField CreationDate;
    public int iDCustomer=0;
    public String getSex;
    public void SetCustomer(Customer customer){
        iDCustomer = customer.getID();
        FullName.setText(customer.getFullName());
        if(customer.getSex().equalsIgnoreCase("nam")){
            Male.setSelected(true);
            Female.setSelected(false);
        }
        else{
            Male.setSelected(false);
            Female.setSelected(true);
        }
        DateOfBirth.setText(String.valueOf(customer.getDateOfBirth()));
        Address.setText(customer.getAddress());
        Phone.setText(customer.getPhoneNumber());
        CreationDate.setText(String.valueOf(customer.getCreationDate()));

    }
    public void ActionSave(ActionEvent actionEvent) throws IOException {
       try{
           Connection con=ConnectSQL.ConnectDb();
           if(iDCustomer!=0){
               if(!FullName.getText().equalsIgnoreCase("")){
                   if(Male.getText().equalsIgnoreCase("true")){
                       getSex="Nam";
                   }else{
                       getSex="Nu";
                   }
                   LocalDate now=LocalDate.now();
//                   PreparedStatement ps = con.prepareStatement("UPDATE [customer] SET fullname=?, sex=?, address=?, dateofbirth=?, phonenumber=? WHERE idcustomer=?");
//                   ps.setString(1,FullName.getText());
//                   ps.setString(2,getSex);
//                   ps.setString(3,Address.getText());
//                   ps.setDate(4, Date.valueOf(DateOfBirth.getText()));
//                   ps.setString(5,Phone.getText());
//                   ps.setInt(6,iDCustomer);
                   Save.getScene().getWindow().hide();
                   Stage Customer = new Stage();
                   Parent root = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
                   Scene scene = new Scene(root);
                   Customer.setResizable(false);
                   Customer.setScene(scene);
                   Customer.show();
               }
           }
           else{
               if(Male.getText().equalsIgnoreCase("true")){
                   getSex="Nam";
               }else{
                   getSex="Nu";
               }
               LocalDate now=LocalDate.now();
//               PreparedStatement ps = con.prepareStatement("Insert Into [customer](fullname,sex,address,dateofbirth,phonenumber,creationdate) VALUES (?,?,?,?,?,?)");
//               ps.setString(1,FullName.getText());
//               ps.setString(2,getSex);
//               ps.setString(3,Address.getText());
//               ps.setDate(4, Date.valueOf(DateOfBirth.getText()));
//               ps.setString(5,Phone.getText());
//               ps.setDate(6, Date.valueOf(now));
               Save.getScene().getWindow().hide();
               Stage Customer = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
               Scene scene = new Scene(root);
               Customer.setResizable(false);
               Customer.setScene(scene);
               Customer.show();

           }
       }catch (Exception ex){
           JOptionPane.showMessageDialog(null, ex);
       }
    }
    public void ActionAdd(ActionEvent actionEvent) throws IOException {

    }
    public void ActionBack(ActionEvent actionEvent) throws IOException {
        Back.getScene().getWindow().hide();
        Stage customer = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
        Scene scene = new Scene(root);
        customer.setResizable(false);
        customer.setScene(scene);
        customer.show();
    }
}