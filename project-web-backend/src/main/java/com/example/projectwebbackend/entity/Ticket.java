package com.example.projectwebbackend.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "VeXe")
@Entity
public class Ticket {
    @Id
    @Column(name = "MaVeXe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketid;
    @OneToOne
    @JoinColumn(name = "MaChuyenXe")
    private Trip trip;

    @OneToMany
    @JoinColumn(name = "MaGhe")
    private List<Seat> seatList;

    @OneToOne
    @JoinColumn(name = "MaDiemDon")
    private PickAddress pickAddress;

    @OneToOne
    @JoinColumn(name = "MaDiemTra")
    private ReturnAddress returnAddress;

    public Long getTicketid() {
        return ticketid;
    }

    public void setTicketid(Long ticketid) {
        this.ticketid = ticketid;
    }

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
