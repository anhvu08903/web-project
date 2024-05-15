package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NhaXe")
public class Admin {
    @Id
    @Column(name = "MaNhaXe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminid;

    @Column(name = "TenNhaXe")
    private String adminname;

    @Column(name = "TaiKhoanNhaXe")
    private String adminaccount;

    @Column(name = "MatKhauTK")
    private String adminpassword;

    @Column(name = "SoDienThoai")
    private String adminphone;

    @Column(name = "Email")
    private String adminemail;

    @Column(name = "DiaChi")
    private String adminaddress;

    @JsonIgnore
    @Column(name = "Token")
    private String token;

    @Lob
    @Column(name = "HinhAnh", columnDefinition = "LONGBLOB")
    private byte[] adminImage;


}
