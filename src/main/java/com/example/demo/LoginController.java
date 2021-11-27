package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class LoginController{

    @FXML
    private Button Login;

    @FXML
    private Button SignUp;

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField PassWord;
    @FXML
    private CheckBox Remember;
    @FXML
    public static User UserLogin = new User();


    public void ActionLogin(ActionEvent event )throws IOException {

        if(!(UserName.getText().equals("")||PassWord.getText().equals(""))){

            try {
               Connection con = ConnectSQL.ConnectDb(); // ket noi database
                PreparedStatement ps = con.prepareStatement("SELECT * FROM [user] WHERE username =? AND password =?");
                ps.setString(1,UserName.getText());
                ps.setString(2, PassWord.getText());
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    UserLogin.setId(rs.getInt("iduser"));
                    UserLogin.setUserName(rs.getString("username"));
                    UserLogin.setPassWord(rs.getString("password"));
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

            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }


        //}
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
        SignUp.getScene().getWindow().hide();
        Stage Resigter = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        Resigter.setResizable(false);
        Resigter.setScene(scene);
        Resigter.show();

}
}