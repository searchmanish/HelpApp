package com.softcodeinfotech.helpapp.beanresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddHelpListResponse {

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

    public AddHelpListResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AddHelpListResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public AddHelpListResponse withInformation(Information information) {
        this.information = information;
        return this;
    }
    public class Information {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("help_title")
        @Expose
        private String helpTitle;
        @SerializedName("help_description")
        @Expose
        private String helpDescription;
        @SerializedName("help_category_id")
        @Expose
        private String helpCategoryId;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Information withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public String getHelpTitle() {
            return helpTitle;
        }

        public void setHelpTitle(String helpTitle) {
            this.helpTitle = helpTitle;
        }

        public Information withHelpTitle(String helpTitle) {
            this.helpTitle = helpTitle;
            return this;
        }

        public String getHelpDescription() {
            return helpDescription;
        }

        public void setHelpDescription(String helpDescription) {
            this.helpDescription = helpDescription;
        }

        public Information withHelpDescription(String helpDescription) {
            this.helpDescription = helpDescription;
            return this;
        }

        public String getHelpCategoryId() {
            return helpCategoryId;
        }

        public void setHelpCategoryId(String helpCategoryId) {
            this.helpCategoryId = helpCategoryId;
        }

        public Information withHelpCategoryId(String helpCategoryId) {
            this.helpCategoryId = helpCategoryId;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Information withImage(String image) {
            this.image = image;
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Information withLatitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Information withLongitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

    }
}
