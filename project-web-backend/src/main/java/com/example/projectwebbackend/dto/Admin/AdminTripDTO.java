package com.example.projectwebbackend.dto.Admin;

import com.example.projectwebbackend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminTripDTO {
    private Trip trip;

    private Admin admin;

    private Seat seat;

//    private List<Seat> seat;



    private List<PickAddress> pickAddress;

    private List<ReturnAddress> returnAddress;




}
