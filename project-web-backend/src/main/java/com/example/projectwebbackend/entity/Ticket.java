package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "VeXe")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @Column(name = "MaVeXe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketid;
    @ManyToOne
    @JoinColumn(name = "MaChuyenXe", unique = false)
    private Trip trip;

    @OneToMany
    @JsonBackReference
    @JoinColumn(name = "MaGhe")
    private List<Seat> seatList;

    @ManyToOne
    @JoinColumn(name = "MaDiemDon", unique = false)
    private PickAddress pickAddress;

    @ManyToOne
    @JoinColumn(name = "MaDiemTra", unique = false)
    private ReturnAddress returnAddress;

   @Column (name ="vitrighe")
   private String seatlocation;

    @Column (name = "status")
    private String status;
}
