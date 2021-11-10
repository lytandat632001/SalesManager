package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectSQL {
    Connection con =null;

    public static Connection ConnectDb(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;databaseName=salesdb;user=sa;password=Dat123");
            //JOptionPane.showMessageDialog(null,"Kết nối thành công Database ");
            return con;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<User> getDataUser(){
        Connection con = ConnectDb();
        ObservableList list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM [user]");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(getDataUser());
               int iduser = rs.getInt((0));
               String username = rs.getString((1));
               String password = rs.getString((2));
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle(String.valueOf(iduser));
               alert.setContentText("username: "+username + " password: "+password);


            }
            rs.close();
        } catch (Exception e){

        }
        return list;
    }
}