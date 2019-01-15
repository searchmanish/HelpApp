package com.softcodeinfotech.helpapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SigninResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Information")
    @Expose
    private Information information;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SigninResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SigninResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public SigninResponse withInformation(Information information) {
        this.information = information;
        return this;
    }

    public class Information {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("profilestatus")
        @Expose
        private String profilestatus;
        @SerializedName("aadhar")
        @Expose
        private String aadhar;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("pin")
        @Expose
        private String pin;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Information withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Information withName(String name) {
            this.name = name;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Information withEmail(String email) {
            this.email = email;
            return this;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Information withMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Information withAge(String age) {
            this.age = age;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Information withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public String getProfilestatus() {
            return profilestatus;
        }

        public void setProfilestatus(String profilestatus) {
            this.profilestatus = profilestatus;
        }

        public Information withProfilestatus(String profilestatus) {
            this.profilestatus = profilestatus;
            return this;
        }

        public String getAadhar() {
            return aadhar;
        }

        public void setAadhar(String aadhar) {
            this.aadhar = aadhar;
        }

        public Information withAadhar(String aadhar) {
            this.aadhar = aadhar;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Information withAddress(String address) {
            this.address = address;
            return this;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Information withState(String state) {
            this.state = state;
            return this;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public Information withPin(String pin) {
            this.pin = pin;
            return this;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Information withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

    }
}
