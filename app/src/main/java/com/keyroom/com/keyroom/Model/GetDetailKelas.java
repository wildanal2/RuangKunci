package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDetailKelas {
    @SerializedName("status")
    String status;
    @SerializedName("kelas")
    Kelas DataKelas;
    @SerializedName("jadwal")
    List<Jadwal> listDatajadwal;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Kelas getDataKelas() {
        return DataKelas;
    }
    public void setDataKelas(Kelas dataKelas) {
        DataKelas = dataKelas;
    }

    public List<Jadwal> getListDatajadwal() {
        return listDatajadwal;
    }
    public void setListDatajadwal(List<Jadwal> listDatajadwal) {
        this.listDatajadwal = listDatajadwal;
    }
}
