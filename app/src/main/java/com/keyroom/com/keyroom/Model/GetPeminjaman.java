package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPeminjaman {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Peminjaman> listPeminjaman;
    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Peminjaman> getListPeminjaman() {
        return listPeminjaman;
    }
    public void setListPeminjaman(List<Peminjaman> listPeminjaman) {
        this.listPeminjaman = listPeminjaman;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
