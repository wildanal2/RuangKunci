package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    String id;
    @SerializedName("nim")
    String nim;
    @SerializedName("nama")
    String nama;
    @SerializedName("email")
    String email;
    @SerializedName("kelas")
    String kelas;
    @SerializedName("password")
    String password;
    @SerializedName("img")
    String img;
    @SerializedName("level")
    String level;

    public User(String id, String nim, String nama, String email, String kelas, String password, String img, String level) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.kelas = kelas;
        this.password = password;
        this.img = img;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
