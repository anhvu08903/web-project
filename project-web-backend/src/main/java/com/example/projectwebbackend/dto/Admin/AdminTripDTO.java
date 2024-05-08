package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;

public class AdminTripDTO {
    private Trip trip;

private Admin admin;

    private Seat seat;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public AdminTripDTO(Trip trip, Admin admin, Seat seat) {
        this.trip = trip;
        this.admin = admin;
        this.seat = seat;
    }

    public AdminTripDTO() {
    }
}
