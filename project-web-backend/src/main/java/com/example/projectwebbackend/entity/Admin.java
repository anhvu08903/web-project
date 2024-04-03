package com.example.projectwebbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "NhaXe")
public class Admin {
    @Id
    @Column(name = "MaNhaXe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminid;

    @Column(name = "TenNhaXe")
    private String adminname;

    @Column(name = "TaiKhoanNhaXe")
    private String adminaccount;

    @Column(name = "MatKhauTK")
    private String adminpassword;

    @Column(name = "SoDienThoai")
    private String adminphone;

    @Column(name = "Email")
    private String adminemail;

    @Column(name = "DiaChi")
    private String adminaddress;

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
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
