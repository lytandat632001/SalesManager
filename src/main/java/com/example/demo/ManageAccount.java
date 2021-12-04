package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManageAccount implements Initializable {
    @FXML
    private TextField UserName;
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
    private TextField CreationDate;

    @FXML
    private MenuButton MenuAccount;
    @FXML
    private MenuItem LogOut;
    @FXML
    private MenuItem Exit;
    @FXML
    private MenuButton MenuCustomer;
    @FXML
    private MenuItem CustomerList;
    @FXML
    private MenuItem SalesLead;
    @FXML
    private Button Back;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con = ConnectSQL.ConnectDb();
            PreparedStatement ps =con.prepareStatement("SELECT * FROM account WHERE iduser=?");
            ps.setInt(1,LoginController.UserLogin.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                UserName.setText(String.valueOf(rs.getInt("iduser")));
                FullName.setText(rs.getString("fullname"));
                if (rs.getString("sex").equalsIgnoreCase("nam")){
                    Male.setSelected(true);
                }else{
                    Female.setSelected(true);
                }
                if(!(rs.getDate("dateofbirth")==null)){
                    DateOfBirth.setText(String.valueOf(rs.getDate("dateofbirth")));
                }else{
                    DateOfBirth.setText("");
                }
                Address.setText(rs.getString("address"));
                Phone.setText(rs.getString("phonenumber"));
                CreationDate.setText(String.valueOf(rs.getDate("creationdate")));
            }

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        LogOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    Stage Login = new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene = new Scene(root);
                    Login.setResizable(false);
                    Login.setScene(scene);
                    Login.show();
                    MenuAccount.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();

            }
        });
        CustomerList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Stage Customers = new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene = new Scene(root);
                    Customers.setResizable(false);
                    Customers.setScene(scene);
                    Customers.show();
                    MenuCustomer.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void ActionBack(ActionEvent event)throws IOException{
        Back.getScene().getWindow().hide();
        Stage MenuTemp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListMenu.fxml"));
        Scene scene = new Scene(root);
        MenuTemp.setScene(scene);
        MenuTemp.setResizable(false);
        MenuTemp.show();

    }
    public void ActionEdit(ActionEvent actionEvent)throws IOException {
        boolean flag=true;

                if (!FullName.getText().equalsIgnoreCase("")) {
                    String getSex=null;

                    if (Male.isSelected() == flag) {
                        getSex = "Nam";
                    } else {
                        getSex = "Nữ";
                    }
                    try {
                        Connection con=ConnectSQL.ConnectDb();
                    PreparedStatement ps = con.prepareStatement("UPDATE account SET fullname=?, sex=?,address=?,dateofbirth=?,phonenumber=? WHERE iduser=?");
                    ps.setString(1, FullName.getText());
                    ps.setString(2, getSex);
                    ps.setString(3, Address.getText());
                    ps.setDate(4, Date.valueOf(DateOfBirth.getText()));
                    ps.setString(5, Phone.getText());
                    ps.setInt(6, LoginController.UserLogin.getId());
                    ps.executeUpdate();
                }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,exception);
                    }
            }
    }




}
