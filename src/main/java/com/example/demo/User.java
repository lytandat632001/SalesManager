package com.example.demo;

public class User {
    private int id;
    private String userName;
    private String passWord;
    public User(){

    }
    public User(String UserName,String PassWord,int id){
        this.id=id;
        this.userName=UserName;
        this.passWord=PassWord;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
