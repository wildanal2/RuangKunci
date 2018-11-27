package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("id")
    private String id;
    @SerializedName("id_kelas")
    private String id_kelas;
    @SerializedName("hari")
    private String hari;
    @SerializedName("waktu_mulai")
    private String waktu_mulai;
    @SerializedName("waktu_selesai")
    private String waktu_selesai;
    @SerializedName("matkul")
    private String matkul;
    @SerializedName("sks")
    private String sks;
    @SerializedName("jam")
    private String jam;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("pengajar")
    private String pengajar;

    public Jadwal(String id, String id_kelas, String hari, String waktu_mulai, String waktu_selesai, String matkul, String sks, String jam, String kelas, String pengajar) {
        this.id = id;
        this.id_kelas = id_kelas;
        this.hari = hari;
        this.waktu_mulai = waktu_mulai;
        this.waktu_selesai = waktu_selesai;
        this.matkul = matkul;
        this.sks = sks;
        this.jam = jam;
        this.kelas = kelas;
        this.pengajar = pengajar;
    }

    public String getId_kelas() {
        return id_kelas;
    }
    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getSks() {
        return sks;
    }
    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getJam() {
        return jam;
    }
    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getWaktu_mulai() {
        return waktu_mulai;
    }
    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }
    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }
    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getMatkul() {
        return matkul;
    }
    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getKelas() {
        return kelas;
    }
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPengajar() {
        return pengajar;
    }
    public void setPengajar(String pengajar) {
        this.pengajar = pengajar;
    }
}
