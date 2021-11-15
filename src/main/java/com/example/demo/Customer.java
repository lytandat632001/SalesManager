package com.example.demo;

import java.sql.Date;

public class Customer {
    private int ID;
    private String FullName;
    private String PhoneNumber;
    private String Sex;
    private String address;
    private Date DateOfBirth;
    private String Note;

    public Customer(int ID, String fullName, String phoneNumber, String sex, String address, Date dateOfBirth, String note) {
        this.ID = ID;
        FullName = fullName;
        PhoneNumber = phoneNumber;
        Sex = sex;
        this.address = address;
        DateOfBirth = dateOfBirth;
        Note = note;
    }

    public Customer(int ID, String fullName, String phoneNumber, String address, String note) {
        this.ID = ID;
        FullName = fullName;
        PhoneNumber = phoneNumber;
        this.address = address;
        Note = note;
    }

    public Customer(){

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
