package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "DiemTra")
public class ReturnAddress {
    @Id
    @Column(name = "MaDiemTra")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnid;

    @Column(name = "TenDiemTra")
    private String returnaddress;

    @ManyToOne
    @JoinColumn(name = "MaChuyenXe")
    @JsonBackReference
    @JsonIgnore
    private Trip trip;

    public Long getReturnid() {
        return returnid;
    }

    public void setReturnid(Long returnid) {
        this.returnid = returnid;
    }

    public String getReturnaddress() {
        return returnaddress;
    }

    public void setReturnaddress(String returnaddress) {
        this.returnaddress = returnaddress;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
