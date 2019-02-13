package com.softcodeinfotech.helpapp.model;

public class GetHelpListModel {

    String helpTitle;
    String helpTimeStamp;
    String helpDescription;
    String helpCategoryId;
    String helpStatus;
    String helpState;
    String userId;
    String address;
    String latitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String longitude;
    String image;

    public GetHelpListModel(String helpTitle, String helpTimeStamp,
                            String helpDescription, String helpCategoryId,
                            String helpStatus, String helpState, String userId,
                            String address, String latitude,String longitude,String image) {
        this.helpTitle = helpTitle;
        this.helpTimeStamp = helpTimeStamp;
        this.helpDescription = helpDescription;
        this.helpCategoryId = helpCategoryId;
        this.helpStatus = helpStatus;
        this.helpState = helpState;
        this.userId = userId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
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
