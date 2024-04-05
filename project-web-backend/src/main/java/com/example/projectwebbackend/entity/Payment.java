package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "ThanhToan")
public class Payment {
    @Id
    @Column(name = "MaHoaDon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billid;


    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    @JsonBackReference
    @JsonIgnore
    private User user;
    @OneToOne
    @JoinColumn(name = "MaVeXe")
    private Ticket ticket;

    @Column(name = "TongTien")
    private Long totalprice;

    public Long getBillid() {
        return billid;
    }

    public void setBillid(Long billid) {
        this.billid = billid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Long totalprice) {
        this.totalprice = totalprice;
    }
}
