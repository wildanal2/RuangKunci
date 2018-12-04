package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUser {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<User>  AllUser;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getAllUser() {
        return AllUser;
    }

    public void setAllUser(List<User> allUser) {
        AllUser = allUser;
    }
}
