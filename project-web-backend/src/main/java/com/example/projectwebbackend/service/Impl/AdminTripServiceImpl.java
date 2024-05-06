package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Trip;
import com.example.projectwebbackend.repository.TripRepository;
import com.example.projectwebbackend.service.AdminService;
import com.example.projectwebbackend.service.AdminTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTripServiceImpl implements AdminTripService {

    @Autowired private TripRepository tripRepository;
    @Override
    public List<Trip> getAllTrip() {
        return (List<Trip>) tripRepository.findAll();
    }
}
