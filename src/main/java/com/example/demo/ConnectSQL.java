package com.example.demo;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectSQL {
    Connection con = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;databaseName=salesdb;user=sa;password=111111");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}