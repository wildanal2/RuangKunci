package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class Kelas {
    @SerializedName("id")
    private String id;
    @SerializedName("ruang")
    private String ruang;
    @SerializedName("nama_lab")
    private String nama_lab;
    @SerializedName("lokasi")
    private String lokasi;
    @SerializedName("img")
    private  String img;

    public Kelas() { }

    public Kelas(String id, String ruang, String nama_lab, String lokasi, String img) {
        this.id = id;
        this.ruang = ruang;
        this.nama_lab = nama_lab;
        this.lokasi = lokasi;
        this.img = img;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getRuang() {
        return ruang;
    }
    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getNama_lab() {
        return nama_lab;
    }
    public void setNama_lab(String nama_lab) {
        this.nama_lab = nama_lab;
    }

    public String getLokasi() {
        return lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
