package com.example.demo;
import javafx.scene.control.Alert;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;

public class FunctionLoad {
    public static String CheckMale(boolean Male){
        String Sex;
        boolean flag=true;
        if(Male==flag){
            Sex="Nam";
        }else{
            Sex="Nữ";
        }
        return Sex;
    }
    public  static Date CheckDate(String DateTime){
        Date DateFormat=null;
        if(!DateTime.equalsIgnoreCase("")){
            DateFormat= java.sql.Date.valueOf(DateTime);
        }
        return  DateFormat;
    }
    public  static Date now(){
        LocalDate now= LocalDate.now();
        return Date.valueOf(now);
    }
    public static void AlertProgram(String Title, String Content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Title);
        alert.setContentText(Content);
        alert.show();
    }
    public  static Date BeforeNow(){
        LocalDate now= LocalDate.now();
        LocalDate BeforeNow = now.minusDays(17);
        return Date.valueOf(BeforeNow);
    }
}
