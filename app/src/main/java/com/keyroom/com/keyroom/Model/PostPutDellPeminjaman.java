package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDellPeminjaman {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Peminjaman mPeminjam;
    @SerializedName("user")
    User mUser;
    @SerializedName("kelas")
    Kelas mKelas;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Peminjaman getmPeminjam() {
        return mPeminjam;
    }
    public void setmPeminjam(Peminjaman mPeminjam) {
        this.mPeminjam = mPeminjam;
    }

    public User getmUser() {
        return mUser;
    }
    public void setmUser(User mUser) {
        this.mUser = mUser;
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
