package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class Peminjaman {
    @SerializedName("id")
    private String id;
    @SerializedName("id_kelas")
    private String id_kelas;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("mulai")
    private String mulai;
    @SerializedName("selesai")
    private String selesai;
    @SerializedName("status")
    private String status;
    @SerializedName("img")
    private String img;
    @SerializedName("id_admin")
    private String id_admin;
    @SerializedName("ruang")
    private String ruang;
    @SerializedName("nama_lab")
    private String nama_lab;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("k_img")
    private String k_img;
    @SerializedName("p_img")
    private String p_img;

    public String getKelas() {
        return kelas;
    }
    public void setKelas(String kelas) {
        this.kelas = kelas;
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

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getK_img() {
        return k_img;
    }
    public void setK_img(String k_img) {
        this.k_img = k_img;
    }

    public String getP_img() {
        return p_img;
    }
    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId_kelas() {
        return id_kelas;
    }
    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getId_user() {
        return id_user;
    }
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getMulai() {
        return mulai;
    }
    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getSelesai() {
        return selesai;
    }
    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getId_admin() {
        return id_admin;
    }
    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }
}
