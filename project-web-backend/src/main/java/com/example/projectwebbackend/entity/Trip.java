package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
@Table(name = "ChuyenXe")
@Entity
public class Trip {
    @Id
    @Column(name = "MaChuyenXe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripid;

    @Column(name = "ThoiGianDi")
    private Date starttime;

    @Column(name = "ThoiGianDen")
    private Date endtime;

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "MaTinhDi", unique = false)
    private Province startprovince;

    @ManyToOne
    @JoinColumn(name = "MaTinhDen", unique = false)
=======
    @OneToOne
    @JoinColumn(name = "MaTinhDi")
    private Province startprovince;

    @OneToOne
    @JoinColumn(name = "MaTinhDen")
>>>>>>> 4e2521d1613f6dbe93f233927b9d966616911fb5
    private Province endprovince;

    @ManyToOne
    @JoinColumn(name = "BienSoXe")
<<<<<<< HEAD
    @JsonBackReference
=======
//    @JsonBackReference
//    @JsonIgnore
>>>>>>> 4e2521d1613f6dbe93f233927b9d966616911fb5
    private Coach coach;

    public Long getTripid() {
        return tripid;
    }

    public void setTripid(Long tripid) {
        this.tripid = tripid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Province getStartprovince() {
        return startprovince;
    }

    public void setStartprovince(Province startprovince) {
        this.startprovince = startprovince;
    }

    public Province getEndprovince() {
        return endprovince;
    }

    public void setEndprovince(Province endprovince) {
        this.endprovince = endprovince;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
