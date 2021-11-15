package com.example.demo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController  implements Initializable {
    @FXML
    private TableView<Customer> TableCustomer;
    @FXML
    private TableColumn<Customer,Integer> IDCustomer;
    @FXML
    private TableColumn<Customer,String> NameCustomer;
    @FXML
    private TableColumn<Customer,String> Phone;
    @FXML
    private TableColumn<Customer,String> Address;
    @FXML
    private TableColumn<Customer,String> Notes;
    private ObservableList<Customer>CustomerList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;databaseName=salesdb;user=sa;password=Dat123");
            PreparedStatement ps = con.prepareStatement("SELECT idcustomer,fullname,phonenumber,address,Notes FROM [customer]");
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                CustomerList.add(new Customer(rs.getInt("idcustomer"), rs.getString("fullname"),
                        rs.getString("phonenumber"),rs.getString("address"),rs.getString("Notes")));
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
                    IDCustomer.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("ID"));
                    NameCustomer.setCellValueFactory(new PropertyValueFactory<Customer,String>("FullName"));
                    Phone.setCellValueFactory(new PropertyValueFactory<Customer,String>("PhoneNumber"));
                    Address.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
                    Notes.setCellValueFactory(new PropertyValueFactory<Customer,String>("Note"));
                    TableCustomer.setItems(CustomerList);

    }
}
