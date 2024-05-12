package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.*;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminTripDTO {
    private Trip trip;

    private Admin admin;

    private Seat seat;

    private SeatLocation seatLocation;

    private List<PickAddress> pickAddress;

    private List<ReturnAddress> returnAddress;


    public void setPickAddress(List<PickAddress> pickAddress) {
        this.pickAddress = pickAddress;
    }

    public void setReturnAddress(List<ReturnAddress> returnAddress) {
        this.returnAddress = returnAddress;
    }

    public Trip getTrip() {
        return trip;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Seat getSeat() {
        return seat;
    }

    public SeatLocation getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(SeatLocation seatLocation) {
        this.seatLocation = seatLocation;
    }

    public List<PickAddress> getPickAddress() {
        return pickAddress;
    }

    public List<ReturnAddress> getReturnAddress() {
        return returnAddress;
    }

    public AdminTripDTO(Trip trip, Admin admin, Seat seat, SeatLocation seatLocation, List<PickAddress> pickAddress, List<ReturnAddress> returnAddress) {
        this.trip = trip;
        this.admin = admin;
        this.seat = seat;
        this.seatLocation = seatLocation;
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
