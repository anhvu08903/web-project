package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.PickAddress;
import com.example.projectwebbackend.entity.ReturnAddress;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class UserTicketBookingRequest {
    private Long tripid;
    private List<Long> seatid;
    private PickAddress pickAddress;
    private ReturnAddress returnAddress;

    public Long getTripid() {
        return tripid;
    }

    public void setTripid(Long tripid) {
        this.tripid = tripid;
    }

    public List<Long> getSeatid() {
        return seatid;
    }

    public void setSeatid(List<Long> seatid) {
        this.seatid = seatid;
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
