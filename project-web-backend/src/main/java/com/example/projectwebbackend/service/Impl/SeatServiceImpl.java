package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.repository.SeatRepository;
import com.example.projectwebbackend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllSeat() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatByLisencplate(String lisencplate) {
        return seatRepository.findSeatByCoach_Licenseplate(lisencplate);
    }
}
