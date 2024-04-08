package com.example.projectwebbackend.dto;

import jakarta.validation.constraints.Size;

public class UserSigninRequest {
    @Size(min = 4, message = "Tai khoan can it nhat 4 ki tu")
    private String account;
    @Size(min = 6, message = "Mat khau phai co it nhat 6 ky tu")
    private String password;

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
}
