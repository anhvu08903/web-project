package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.Admin.AdminTripDTO;
import com.example.projectwebbackend.dto.Admin.FilterTRip;

import java.util.List;

public interface AdminTripDTOService {
    List<AdminTripDTO> getAllSeatInfo();

    AdminTripDTO getSeatInfoByTripId(Long id);

    List<AdminTripDTO> filterTrip(FilterTRip filterTRip);

    List<AdminTripDTO> getALlSeatByAdminToken(String token);

}
