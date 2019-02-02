package com.softcodeinfotech.helpapp.model;

public class GetHelpListModel {

    String helpTitle;
    String helpTimeStamp;
    String helpDescription;
    String helpCategoryId;
    String helpStatus;
    String helpState;
    String userId;

    public GetHelpListModel(String helpTitle, String helpTimeStamp,
                            String helpDescription, String helpCategoryId,
                            String helpStatus, String helpState, String userId) {
        this.helpTitle = helpTitle;
        this.helpTimeStamp = helpTimeStamp;
        this.helpDescription = helpDescription;
        this.helpCategoryId = helpCategoryId;
        this.helpStatus = helpStatus;
        this.helpState = helpState;
        this.userId = userId;
    }

    public String getHelpTitle() {
        return helpTitle;
    }

    public void setHelpTitle(String helpTitle) {
        this.helpTitle = helpTitle;
    }

    public String getHelpTimeStamp() {
        return helpTimeStamp;
    }

    public void setHelpTimeStamp(String helpTimeStamp) {
        this.helpTimeStamp = helpTimeStamp;
    }

    public String getHelpDescription() {
        return helpDescription;
    }

    public void setHelpDescription(String helpDescription) {
        this.helpDescription = helpDescription;
    }

    public String getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(String helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    public String getHelpStatus() {
        return helpStatus;
    }

    public void setHelpStatus(String helpStatus) {
        this.helpStatus = helpStatus;
    }

    public String getHelpState() {
        return helpState;
    }

    public void setHelpState(String helpState) {
        this.helpState = helpState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
