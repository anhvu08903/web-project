package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;

public class AdminTripDTO {
    private Trip trip;



    private Seat seat;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public AdminTripDTO(Trip trip,  Seat seat) {
        this.trip = trip;

        this.seat = seat;
    }

    public AdminTripDTO() {
    }
}
