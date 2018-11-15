package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("id")
    private String id;
    @SerializedName("hari")
    private String hari;
    @SerializedName("waktu")
    private String waktu;
    @SerializedName("matkul")
    private String matkul;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("pengajar")
    private String pengajar;

    public Jadwal(String id, String hari, String waktu, String matkul, String kelas, String pengajar) {
        this.id = id;
        this.hari = hari;
        this.waktu = waktu;
        this.matkul = matkul;
        this.kelas = kelas;
        this.pengajar = pengajar;
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

    public String getWaktu() {
        return waktu;
    }
    public void setWaktu(String waktu) {
        this.waktu = waktu;
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
