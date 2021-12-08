package com.example.demo;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectSQL {



    public  static Connection ConnectDb() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;databaseName=salesdb;user=sa;password=111111");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return con;
    }
}