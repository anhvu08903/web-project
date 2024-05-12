package com.example.projectwebbackend.service;

import com.example.projectwebbackend.entity.Seat;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface SeatService {

    List<Seat> getAllSeat();

    Seat getSeatByLisencplate(String lisencplate);
}
