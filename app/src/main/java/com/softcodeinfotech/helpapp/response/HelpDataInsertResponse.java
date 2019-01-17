package com.softcodeinfotech.helpapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HelpDataInsertResponse {

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

    public HelpDataInsertResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HelpDataInsertResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public HelpDataInsertResponse withInformation(Information information) {
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

    }
}
