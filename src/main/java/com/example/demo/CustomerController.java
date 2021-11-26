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

public class    CustomerController  implements Initializable {
    @FXML
    private TableView<Customer> TableCustomer;
    @FXML
    private TableColumn<Customer,Integer> IDCustomer;
    @FXML
    private TableColumn<Customer,String> NameCustomer;
    @FXML
    private TableColumn<Customer,String> SexCustomer;
    @FXML
    private TableColumn<Customer,String> Address;
    @FXML
    private TableColumn<Customer,Date> DateOfBirth;
    @FXML
    private TableColumn<Customer,String> Phone;
    @FXML
    private TableColumn<Customer,Date> CreationDate;
    private ObservableList<Customer>CustomerList = FXCollections.observableArrayList();
    public User user = new User();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con = ConnectSQL.ConnectDb(); // ket noi database
            PreparedStatement ps = con.prepareStatement("SELECT * FROM [customer] Where iduser=?");
            ps.setInt(1,LoginController.UserLogin.getId());
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                CustomerList.add(new Customer(rs.getInt("idcustomer"), rs.getString("fullname"),
                        rs.getString("sex"), rs.getString("address"),rs.getDate("dateofbirth"),
                        rs.getString("phonenumber"),rs.getDate("creationdate")));
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
                    IDCustomer.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("ID"));
                    NameCustomer.setCellValueFactory(new PropertyValueFactory<Customer,String>("FullName"));
                    SexCustomer.setCellValueFactory(new PropertyValueFactory<Customer,String>("Sex"));
                    Address.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
                    DateOfBirth.setCellValueFactory(new PropertyValueFactory<Customer,Date>("DateOfBirth"));
                    Phone.setCellValueFactory(new PropertyValueFactory<Customer,String>("PhoneNumber"));
                    CreationDate.setCellValueFactory(new PropertyValueFactory<Customer,Date>("CreationDate"));
                    TableCustomer.setItems(CustomerList);
    }
}
