package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.*;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminTripDTO {
    private Trip trip;

    private Admin admin;

    private Seat seat;

    private List<PickAddress> pickAddress;

    private List<ReturnAddress> returnAddress;


    public void setPickAddress(List<PickAddress> pickAddress) {
        this.pickAddress = pickAddress;
    }

    public void setReturnAddress(List<ReturnAddress> returnAddress) {
        this.returnAddress = returnAddress;
    }

    public AdminTripDTO(Trip trip, Admin admin, Seat seat, List<PickAddress> pickAddress, List<ReturnAddress> returnAddress) {
        this.trip = trip;
        this.admin = admin;
        this.seat = seat;
        this.pickAddress = pickAddress;
        this.returnAddress = returnAddress;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }



    public AdminTripDTO() {
    }
}
