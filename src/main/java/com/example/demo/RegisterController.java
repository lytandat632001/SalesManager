package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private Button BackLogin;

    @FXML
    private Button RegisterMore;

    @FXML
    private TextField UserRegister;
    @FXML
    private TextField PassRegister;
    @FXML
    private TextField PassRegisterAgain;

    @FXML
    void ActionBackLogin(ActionEvent event) throws IOException {
        BackLogin.getScene().getWindow().hide();
        Stage Login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Login.setResizable(false);
        Login.setScene(scene);
        Login.show();
    }

    @FXML
    void ActionRegisterMore(ActionEvent event) throws IOException{
    String UserTemp = UserRegister.getText();
    String PassTemp = PassRegister.getText();
    String PassTempAgain = PassRegisterAgain.getText();
    ManageUser CurrentUser=new ManageUser();
    ManageUser CreateUser = new ManageUser();
    if(!(UserTemp.equals("") || PassTemp.equals("") || PassTempAgain.equals(""))){
        if(PassTemp.equals(PassTempAgain)){
            RegisterMore.getScene().getWindow().hide();
            Stage Login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            Login.setResizable(false);
            Login.setScene(scene);
            Login.show();
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Đăng kí không thành công!");
            alert.setContentText("Mật khẩu không trùng nhau! Vui lòng thử lại.");
            alert.show();
        }
    }
        
    else
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Đăng kí không thành công!");
        alert.setContentText("Bạn chưa nhập đủ thông tin đăng ký! Vui lòng thử lại.");
        alert.show();

    }

    }

}

