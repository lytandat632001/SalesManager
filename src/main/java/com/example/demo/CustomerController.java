package com.example.demo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController  implements Initializable {
    @FXML
    private MenuItem AddCustomer;
    @FXML
    private MenuItem EditCustomer;
    @FXML
    private MenuItem RemoveCustomer;
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
    private MenuButton Menu;
    @FXML
    private TableColumn<Customer,Date> CreationDate;
    private ObservableList<Customer>CustomerList = FXCollections.observableArrayList();
    public User user = new User();
    Connection con = ConnectSQL.ConnectDb();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
             // ket noi database
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
        AddCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    Stage ChangeCustomer = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("ChangeCustomer.fxml"));
                    Scene scene = new Scene(root);
                    ChangeCustomer.setResizable(false);
                    ChangeCustomer.setScene(scene);
                    ChangeCustomer.show();
                    Menu.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        });
        EditCustomer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                try {

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ChangeCustomer.fxml"));
                    Parent studentViewParent = loader.load();
                    Scene scene = new Scene(studentViewParent);
                    ChangeCustomer controller = loader.getController();
                    Customer selected = TableCustomer.getSelectionModel().getSelectedItem();
                    controller.SetCustomer(selected);
                    stage.setScene(scene);
                    stage.show();
                    Menu.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        RemoveCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Customer CustomerRemove = TableCustomer.getSelectionModel().getSelectedItem();
                TableCustomer.getItems().remove(CustomerRemove);

                try {
                    PreparedStatement ps = con.prepareStatement("DELETE FROM [customer] WHERE idcustomer =?" );
                    ps.setInt(1,CustomerRemove.getID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
