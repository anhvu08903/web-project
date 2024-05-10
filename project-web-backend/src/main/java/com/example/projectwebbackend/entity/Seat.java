package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Ghe")
public class Seat {
    @Id
    @Column(name = "MaGhe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatid;

    @Column(name = "GiaGhe")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "BienSoXe")
    @JsonBackReference
    private Coach coach;

    @Column(name = "LoaiGhe")
    private String type;

    @ManyToOne
    @JoinColumn(name = "MaViTri")
    @JsonBackReference
    private SeatLocation seatLocation;

    public Long getSeatid() {
        return seatid;
    }

    public void setSeatid(Long seatid) {
        this.seatid = seatid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SeatLocation getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(SeatLocation seatLocation) {
        this.seatLocation = seatLocation;
    }
}
