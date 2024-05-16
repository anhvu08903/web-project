package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Table(name = "ChuyenXe")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @Column(name = "MaChuyenXe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripid;

    @Column(name = "SoGheConLai")
    private Integer remainingSeat;

    @Column(name = "ThoiGianDi")
    private Date starttime;

    @Column(name = "ThoiGianDen")
    private Date endtime;

    @ManyToOne
    @JoinColumn(name = "MaTinhDi", unique = false)
    private Province startprovince;

    @ManyToOne
    @JoinColumn(name = "MaTinhDen", unique = false)
    private Province endprovince;

    @ManyToOne
    @JoinColumn(name = "BienSoXe")

    private Coach coach;


}
