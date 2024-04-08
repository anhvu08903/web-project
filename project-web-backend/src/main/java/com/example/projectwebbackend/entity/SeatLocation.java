package com.example.projectwebbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ViTriGhe")
public class SeatLocation {
    @Id
    @Column(name = "MaViTri")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationid;

    @Column(name = "ViTri")
    private String coordinate;

    public Long getLocationid() {
        return locationid;
    }

    public void setLocationid(Long locationid) {
        this.locationid = locationid;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
