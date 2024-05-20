package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.Admin.AdminTripDTO;
import com.example.projectwebbackend.dto.Admin.FilterTRip;
import com.example.projectwebbackend.entity.Trip;

import java.util.List;

public interface AdminTripDTOService {
    List<AdminTripDTO> getAllSeatInfo();

    AdminTripDTO getSeatInfoByTripId(Long id);

    List<AdminTripDTO> filterTrip(FilterTRip filterTRip);

    List<AdminTripDTO> getALlSeatByAdminToken(String token);

    List<Trip> getTripByDate(String date);
}
