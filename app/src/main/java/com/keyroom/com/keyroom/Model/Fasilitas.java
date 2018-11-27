package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class Fasilitas {

    @SerializedName("id")
    private String id;
    @SerializedName("id_ruang")
    private String id_ruang;
    @SerializedName("nama_fasilitas")
    private String nama_fasilitas;
    @SerializedName("jumlah")
    private String jumlah;

    public Fasilitas(String id, String id_ruang, String nama_fasilitas, String jumlah) {

        this.id = id;
        this.id_ruang = id_ruang;
        this.nama_fasilitas = nama_fasilitas;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId_ruang() {
        return id_ruang;
    }
    public void setId_ruang(String id_ruang) {
        this.id_ruang = id_ruang;
    }

    public String getNama_fasilitas() {
        return nama_fasilitas;
    }
    public void setNama_fasilitas(String nama_fasilitas) {
        this.nama_fasilitas = nama_fasilitas;
    }

    public String getJumlah() {
        return jumlah;
    }
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
