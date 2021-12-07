package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class RegisterController {

    @FXML
    private Button BackLogin;

    @FXML
    private Button RegisterMore;

    @FXML
    private TextField UserRegister;
    @FXML
    private PasswordField PassRegister;
    @FXML
    private PasswordField PassRegisterAgain;
    @FXML
    private TextField SecretQuestion;

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
    if(!(UserRegister.getText().equals("") || PassRegister.getText().equals("") || PassRegisterAgain.getText().equals("") || SecretQuestion.getText().equals(""))){
        if(PassRegister.getText().equals(PassRegisterAgain.getText())){

            try {
                Connection con = ConnectSQL.ConnectDb(); // ket noi database
                PreparedStatement psUser = con.prepareStatement("SELECT * FROM [user] WHERE username =? AND password =?");
                psUser.setString(1,UserRegister.getText());
                psUser.setString(2, PassRegister.getText());
                ResultSet rsUser = psUser.executeQuery();
                if (!rsUser.next()){
                            PreparedStatement ps = con.prepareStatement("Insert Into [user](username,password,secretquestion) VALUES (?,?,?)");
                            ps.setString(1,UserRegister.getText());
                            ps.setString(2,PassRegister.getText());
                            ps.setString(3,SecretQuestion.getText());
                            ps.executeUpdate();
                            RegisterMore.getScene().getWindow().hide();
                            Stage Login = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                            Scene scene = new Scene(root);
                            Login.setTitle("GoodFriend");
                            Login.setResizable(false);
                            Login.setScene(scene);
                            Login.show();
                }
                else{
                    String Title="Đăng ký thất bại!";
                    String Content="Tên người dùng đã tồn tại! Vui lòng sử dụng tên người dùng đăng ký khác.";
                    FunctionLoad.AlertProgram(Title,Content);
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }

        }
        else
        {
            String Title="Đăng ký thất bại!";
            String Content="Mật khẩu không trùng khớp!";
            FunctionLoad.AlertProgram(Title,Content);
        }
    }
        
    else
    {
        String Title="Đăng ký thất bại!";
        String Content="Vui lòng nhập đầy đủ thông tin!";
        FunctionLoad.AlertProgram(Title,Content);

    }

    }

}

