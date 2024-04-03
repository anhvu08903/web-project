package com.example.projectwebbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Xe")
public class Coach {
    @Id
    @Column(name = "BienSoXe")
    private String licenseplate;

    @Column(name = "LoaiXe")
    private String coachtype;

    @Column(name = "SoGheNgoi")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "MaNhaXe")
    private Admin admin;

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getCoachtype() {
        return coachtype;
    }

    public void setCoachtype(String coachtype) {
        this.coachtype = coachtype;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
