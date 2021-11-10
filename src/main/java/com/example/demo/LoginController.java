package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController{

    @FXML
    private Button Login;

    @FXML
    private Button Register;

    @FXML
    private TextField UserName;

    @FXML
    private TextField PassWord;
    @FXML
    private CheckBox Remember;

    @FXML
    public void ActionLogin(ActionEvent event )throws IOException {
        String UserTemp = UserName.getText();
        String PassTemp = PassWord.getText();

        ManageUser UserLogin = new ManageUser();
        if(!(UserTemp.equals("")||PassTemp.equals(""))){
        if(UserLogin.user.getUserName().equals(UserTemp)
                &&UserLogin.user.getPassWord().equals(PassTemp)){

            Login.getScene().getWindow().hide();
            Stage MenuTemp = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ListMenu.fxml"));
            Scene scene = new Scene(root);
            MenuTemp.setScene(scene);
            MenuTemp.setResizable(false);
            MenuTemp.show();


        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Đăng nhập không thành công");
            alert.setContentText("Tài khoản hoặc mật khẩu không chính xác");
            alert.show();
        }

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Đăng nhập không thành công");
            alert.setContentText("Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
            alert.show();
        }
        }


    @FXML
    public void ActionRegister (ActionEvent event) throws IOException {
        Register.getScene().getWindow().hide();
        Stage Resigter = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        Resigter.setResizable(false);
        Resigter.setScene(scene);
        Resigter.show();

}
}