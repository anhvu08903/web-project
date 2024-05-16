package com.example.projectwebbackend.dto.Admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AdminSignInRequest {

    @Size(min = 4, message = "ten tai khoan phai co it nhat 4 ki tu")
    private String adminaccount;


    @Size(min = 6, message = "mat khau phai co it nhat 6 ki tu")

    @Size(min = 6, message = "mat khau phai co it nhat 8 ki tu")
    private String adminpassword;


    public AdminSignInRequest() {
    }

    public AdminSignInRequest(String adminaccount, String adminpassword) {
        this.adminaccount = adminaccount;
        this.adminpassword = adminpassword;

    }



    public String getAdminaccount() {
        return adminaccount;
    }

    public void setAdminaccount(String adminaccount) {
        this.adminaccount = adminaccount;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }


}
