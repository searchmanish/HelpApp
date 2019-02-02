package com.softcodeinfotech.helpapp.model;

public class GetAllHelperListModel {

    String userId;
    String name;
    String email;
    String mobile;
    String age;
    String gender;
    String profilestatus;
    String aadhar;
    String address;
    String state;
    String pin;
    String imageUrl;

    public GetAllHelperListModel(String userId, String name, String email,
                                 String mobile, String age, String gender,
                                 String profilestatus, String aadhar,
                                 String address, String state, String pin, String imageUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
        this.profilestatus = profilestatus;
        this.aadhar = aadhar;
        this.address = address;
        this.state = state;
        this.pin = pin;
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilestatus() {
        return profilestatus;
    }

    public void setProfilestatus(String profilestatus) {
        this.profilestatus = profilestatus;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
