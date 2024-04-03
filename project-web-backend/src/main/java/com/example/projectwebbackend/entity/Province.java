package com.example.projectwebbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tinh")
public class Province {
    @Id
    @Column(name = "MaTinh")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "TenTinh")
    private String pname;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
