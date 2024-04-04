package com.example.projectwebbackend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreationRequest {
    @NotNull
    private String name;
    @Size(min = 4, message = "Tai khoan can it nhat 4 ki tu")
    private String account;
    @Size(min = 6, message = "Mat khau phai co it nhat 6 ky tu")
    private String password;
    @Size(min = 9, max = 10, message = "SDT can co it nhat 9 chu so va nhieu nhat 10 chu so")
    private String phone;
    @NotNull
    private String email;

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
