package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.Trip;


public class AdminTicketPrompt {
    private String status;
    private Trip trip;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public AdminTicketPrompt(String status, Trip trip) {
        this.status = status;
        this.trip = trip;
    }

    public AdminTicketPrompt() {
    }
}
