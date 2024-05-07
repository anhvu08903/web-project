package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
@Table(name = "NguoiDung")
@Entity
public class User {
    @Id
    @Column(name = "MaNguoiDung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Ten")
    private String name;
    @Column(name = "TaiKhoan")
    private String account;
    @Column(name = "MatKhau")
    private String password;
    @Column(name = "SoDienThoai")
    private String phone;
    @Column(name = "Email")
    private String email;

    @JsonIgnore
    @Column(name = "Token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
