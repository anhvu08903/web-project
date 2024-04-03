package com.example.projectwebbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DiemDon")
public class PickAddress {
    @Id
    @Column(name = "MaDiemDon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickid;

    @Column(name = "TenDiemDon")
    private String pickname;

    @ManyToOne
    @JoinColumn(name = "MaChuyenXe")
    private Trip trip;

    public Long getPickid() {
        return pickid;
    }

    public void setPickid(Long pickid) {
        this.pickid = pickid;
    }

    public String getPickname() {
        return pickname;
    }

    public void setPickname(String pickname) {
        this.pickname = pickname;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
