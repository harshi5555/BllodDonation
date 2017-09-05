package com.example.mapp.giveaway;

import java.io.Serializable;

/**
 * Created by harshi on 18/03/17.
 */

public class UserInformation implements Serializable {
    private String city;
    private String address;
    private String age;
    private String bloodGroup;
    private String email;
    private String gender;
    private String name;
    private String passWord;
    private String phoneNumber1;

    private static final long serialVersionUID = 7871545949226261818L;

    public UserInformation() {
    }

    public UserInformation(String city, String address, String age, String bloodGroup, String email, String gender, String name, String passWord, String phoneNumber1) {
        this.city =city;
        this.address = address;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.passWord = passWord;
        this.phoneNumber1 = phoneNumber1;
    }

    public UserInformation(String s, String harshi) {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String toString(){
        return "name "+name+ ", bloodGroup "+bloodGroup;
    }

}