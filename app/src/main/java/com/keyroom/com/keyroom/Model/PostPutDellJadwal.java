package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellJadwal {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Jadwal mJadwal;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Jadwal getmJadwal() {
        return mJadwal;
    }
    public void setmJadwal(Jadwal mJadwal) {
        this.mJadwal = mJadwal;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
