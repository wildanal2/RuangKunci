package com.keyroom.com.keyroom.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("nim")
    String nis;
    @SerializedName("nama")
    String nama;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("img")
    String img;
    @SerializedName("level")
    String level;

    public User(String nis, String nama, String email, String password, String img, String level) {
        this.nis = nis;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.img = img;
        this.level = level;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
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
