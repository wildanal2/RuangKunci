package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellKelas {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kelas mKelas;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Kelas getmKelas() {
        return mKelas;
    }
    public void setmKelas(Kelas mKelas) {
        this.mKelas = mKelas;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
