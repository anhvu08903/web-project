package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.PickAddress;
import com.example.projectwebbackend.entity.ReturnAddress;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;

import java.util.List;

public class UserTicketBookingRequest {
    private Trip trip;
    private List<Seat> seatList;
    private PickAddress pickAddress;
    private ReturnAddress returnAddress;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public PickAddress getPickAddress() {
        return pickAddress;
    }

    public void setPickAddress(PickAddress pickAddress) {
        this.pickAddress = pickAddress;
    }

    public ReturnAddress getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(ReturnAddress returnAddress) {
        this.returnAddress = returnAddress;
    }
}
