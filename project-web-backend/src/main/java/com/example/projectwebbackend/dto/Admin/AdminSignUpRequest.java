package com.example.projectwebbackend.dto.Admin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AdminSignUpRequest {
    @NotNull
    private String adminname;

    @Size(min = 4, message = "ten tai khoan phai co it nhat 4 ki tu")
    private String adminaccount;

    @Size(min = 8, message = "mat khau phai co it nhat 8 ki tu")
    private String adminpassword;

    @Size(min = 4, max =10,  message = "vui long nhap so dien thoai hop le ( 9 hoac 10 so)")
    private String adminphone;

    private String adminemail;

    private String adminaddress;

    public AdminSignUpRequest() {
    }

    public AdminSignUpRequest(String adminname, String adminaccount, String adminpassword, String adminphone, String adminemail, String adminaddress) {
        this.adminname = adminname;
        this.adminaccount = adminaccount;
        this.adminpassword = adminpassword;
        this.adminphone = adminphone;
        this.adminemail = adminemail;
        this.adminaddress = adminaddress;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
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

    public String getAdminphone() {
        return adminphone;
    }

    public void setAdminphone(String adminphone) {
        this.adminphone = adminphone;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getAdminaddress() {
        return adminaddress;
    }

    public void setAdminaddress(String adminaddress) {
        this.adminaddress = adminaddress;
    }
}
