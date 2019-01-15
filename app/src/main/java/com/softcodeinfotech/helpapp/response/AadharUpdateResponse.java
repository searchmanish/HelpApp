package com.softcodeinfotech.helpapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AadharUpdateResponse {

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

    public AadharUpdateResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AadharUpdateResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public AadharUpdateResponse withInformation(Information information) {
        this.information = information;
        return this;
    }
    public class Information {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("aadhar")
        @Expose
        private String aadhar;

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

    }
}
