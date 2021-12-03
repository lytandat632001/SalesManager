package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPassword {
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField PassWord;
    @FXML
    private TextField SecretQuestion;
    @FXML
    private Button Reset;
    @FXML
    public void ActionReset(ActionEvent actionEvent)throws IOException {
        if(!UserName.getText().equalsIgnoreCase("") || PassWord.getText().equalsIgnoreCase("") || SecretQuestion.getText().equalsIgnoreCase(""))
                    try{
                        Connection con =ConnectSQL.ConnectDb();
                        PreparedStatement ps = con.prepareStatement("SELECT *FROM [user] WHERE username=? AND secretquestion =?");
                        ps.setString(1,UserName.getText());
                        ps.setString(2,SecretQuestion.getText());
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            PreparedStatement psUpdate = con.prepareStatement("UPDATE [user] SET password =? WHERE username=? AND secretquestion=?");
                            psUpdate.setString(1,PassWord.getText());
                            psUpdate.setString(2,UserName.getText());
                            psUpdate.setString(3,SecretQuestion.getText());
                            psUpdate.executeUpdate();
                            Reset.getScene().getWindow().hide();
                            Stage Login = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                            Scene scene = new Scene(root);
                            Login.setResizable(false);
                            Login.setScene(scene);
                            Login.show();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Khôi phục mật khẩu thất bại!");
                            alert.setContentText("Người dùng không tồn tại! Vui lòng kiểm tra lại!");
                            alert.show();
                        }
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,exception);
                    }


                }
    }
