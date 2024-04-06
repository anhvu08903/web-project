package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.Ticket;
import com.example.projectwebbackend.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class UserPaymentRequest {
    private User user;
    private UserBookTicketRequest userBookTicketRequest;
    private Long totalprice;

    public UserBookTicketRequest getUserBookTicketRequest() {
        return userBookTicketRequest;
    }

    public void setUserBookTicketRequest(UserBookTicketRequest userBookTicketRequest) {
        this.userBookTicketRequest = userBookTicketRequest;
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
