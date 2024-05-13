package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.PickAddress;
import com.example.projectwebbackend.entity.ReturnAddress;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserTicketBookingRequest {
    private Long tripid;
    private List<Long> seatid;
    private PickAddress pickAddress;
    private ReturnAddress returnAddress;
    private String seatlocation;
    private String status;

}
