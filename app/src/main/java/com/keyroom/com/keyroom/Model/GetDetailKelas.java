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
    @SerializedName("fasilitas")
    List<Fasilitas> listFasilitas;
    @SerializedName("riwayat")
    List<Peminjaman> listPeminjaman;

    public List<Peminjaman> getListPeminjaman() {
        return listPeminjaman;
    }
    public void setListPeminjaman(List<Peminjaman> listPeminjaman) {
        this.listPeminjaman = listPeminjaman;
    }

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

    public List<Fasilitas> getListFasilitas() {
        return listFasilitas;
    }
    public void setListFasilitas(List<Fasilitas> listFasilitas) {
        this.listFasilitas = listFasilitas;
    }
}
