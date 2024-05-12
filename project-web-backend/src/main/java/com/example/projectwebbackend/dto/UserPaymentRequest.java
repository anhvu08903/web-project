package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserPaymentRequest {
    private Long ticketid;
    private Long totalprice;

    public Long getTicketid() {
        return ticketid;
    }

    public void setTicketid(Long ticketid) {
        this.ticketid = ticketid;
    }

    public Long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Long totalprice) {
        this.totalprice = totalprice;
    }
}
