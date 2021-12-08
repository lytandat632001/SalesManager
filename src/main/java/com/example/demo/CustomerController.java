package com.example.demo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    private MenuItem PotentialCustomers;
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
    private MenuButton Account;
    @FXML
    private MenuItem LogOut;
    @FXML
    private MenuItem Exit;
    @FXML
    private MenuItem EditAccount;
    @FXML
    private MenuItem CustomersList;
    @FXML
    private TableColumn<Customer,Date> CreationDate;
    private ObservableList<Customer>CustomerList = FXCollections.observableArrayList();
    public User user = new User();
    Connection con = ConnectSQL.ConnectDb();
    @FXML
    private TextField IDSearch;
    @FXML
    private TextField NameSearch;
    @FXML
    private Button Search;
    @FXML
    private Button BackMenu;
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

        //Menu Account
        EditAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage ChangeCustomer = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("ManageAccount.fxml"));
                    Scene scene = new Scene(root);
                    ChangeCustomer.setResizable(false);
                    ChangeCustomer.setScene(scene);
                    ChangeCustomer.show();
                    Account.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
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
                    Account.getScene().getWindow().hide();
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
        //Menu Customer
        CustomersList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    TableCustomer.getItems().removeAll(CustomerList);
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
                TableCustomer.setItems(CustomerList);
            }
        });
        PotentialCustomers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(FunctionLoad.BeforeNow());
                System.out.println(FunctionLoad.now());
                TableCustomer.getItems().removeAll(CustomerList);
                ObservableList<Customer>PontialCustomersList = FXCollections.observableArrayList();
                try {
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM customer where iduser =? AND creationdate between ? AND ?");
                    ps.setInt(1,LoginController.UserLogin.getId());
                    ps.setDate(2,FunctionLoad.BeforeNow());
                    ps.setDate(3,FunctionLoad.now());
                    ResultSet rs= ps.executeQuery();
                    while (rs.next()){
                        PontialCustomersList.add(new Customer(rs.getInt("idcustomer"), rs.getString("fullname"),
                                rs.getString("sex"), rs.getString("address"),rs.getDate("dateofbirth"),
                                rs.getString("phonenumber"),rs.getDate("creationdate")));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);

                }

                TableCustomer.setItems(PontialCustomersList);

            }
        });
        AddCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    Stage ChangeCustomer = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("ChangeCustomer.fxml"));
                    Scene scene = new Scene(root);
                    ChangeCustomer.setTitle("GoodFriend");
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
                    stage.setTitle("GoodFriend");
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
                try {
                    Customer CustomerRemove = TableCustomer.getSelectionModel().getSelectedItem();
                    PreparedStatement ps = con.prepareStatement("DELETE FROM [customer] WHERE idcustomer =?" );
                    ps.setInt(1,CustomerRemove.getID());
                    ps.executeUpdate();
                    TableCustomer.getItems().remove(CustomerRemove);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void ActionSearch(ActionEvent actionEvent)throws IOException{
        TableCustomer.getItems().removeAll(CustomerList);
        ObservableList<Customer>CustomerSearch = FXCollections.observableArrayList();
        if(!(IDSearch.getText().equalsIgnoreCase("") &&
                NameSearch.getText().equalsIgnoreCase(""))){
            try {
                String NameFormat = "%"+NameSearch.getText()+"%";
                PreparedStatement ps;
                ResultSet rs;
                int query=!IDSearch.getText().equalsIgnoreCase("")&&NameSearch.getText().equalsIgnoreCase("")?1:
                        IDSearch.getText().equalsIgnoreCase("")&& !NameSearch.getText().equalsIgnoreCase("")?2:3;
                switch (query){
                    case 1:ps=con.prepareStatement("SELECT * FROM [customer] Where iduser=? AND idcustomer=?");
                    ps.setInt(1,LoginController.UserLogin.getId());
                    ps.setInt(2, Integer.parseInt(IDSearch.getText()));

                    break;
                    case 2:ps=con.prepareStatement("SELECT * FROM [customer] Where iduser=? AND fullname LIKE ? ");
                        ps.setInt(1,LoginController.UserLogin.getId());
                        ps.setString(2,NameFormat);
                        break;
                    default: ps=con.prepareStatement("SELECT * FROM [customer] Where iduser=? AND idcustomer=? AND fullname LIKE ?");
                        ps.setInt(1,LoginController.UserLogin.getId());
                        ps.setInt(2, Integer.parseInt(IDSearch.getText()));
                        ps.setString(3,NameFormat);
                        break;
                }
                rs= ps.executeQuery();
                while (rs.next()){
                    CustomerSearch.add(new Customer(rs.getInt("idcustomer"), rs.getString("fullname"),
                            rs.getString("sex"), rs.getString("address"),rs.getDate("dateofbirth"),
                            rs.getString("phonenumber"),rs.getDate("creationdate")));
                }
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }

            TableCustomer.setItems(CustomerSearch);
        }else {
            String Title="Tìm kiếm thông tin khách hàng thất bài";
            String Content="Vui lòng điền thông tin  tìm kiếm!";
            FunctionLoad.AlertProgram(Title,Content);
        }


    }
    public void ActionBackMenu (ActionEvent event) throws IOException {
        BackMenu.getScene().getWindow().hide();
        Stage Resigter = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListMenu.fxml"));
        Scene scene = new Scene(root);
        Resigter.setTitle("GoodFriend");
        Resigter.setResizable(false);
        Resigter.setScene(scene);
        Resigter.show();

    }
}
