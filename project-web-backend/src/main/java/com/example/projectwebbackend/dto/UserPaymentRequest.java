package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.User;

public class UserPaymentRequest {
    private User user;
    private UserTicketBookingRequest userTicketBookingRequest;
    private Long totalprice;

    public UserTicketBookingRequest getUserBookTicketRequest() {
        return userTicketBookingRequest;
    }

    public void setUserBookTicketRequest(UserTicketBookingRequest userTicketBookingRequest) {
        this.userTicketBookingRequest = userTicketBookingRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Long totalprice) {
        this.totalprice = totalprice;
    }
}
