package com.example.demo;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private boolean remember=false;
    public User(){

    }
    public User(String UserName,String PassWord,int id){
        this.userName=UserName;
        this.passWord=PassWord;
        this.id=id;
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
