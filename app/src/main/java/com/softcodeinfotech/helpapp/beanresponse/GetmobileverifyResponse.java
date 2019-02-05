package com.softcodeinfotech.helpapp.beanresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetmobileverifyResponse {

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

    public GetmobileverifyResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GetmobileverifyResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public GetmobileverifyResponse withInformation(Information information) {
        this.information = information;
        return this;
    }

    public class Information {

        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("sms")
        @Expose
        private String sms;

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

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public Information withOtp(String otp) {
            this.otp = otp;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Information withPassword(String password) {
            this.password = password;
            return this;
        }

        public String getSms() {
            return sms;
        }

        public void setSms(String sms) {
            this.sms = sms;
        }

        public Information withSms(String sms) {
            this.sms = sms;
            return this;
        }

    }
}
