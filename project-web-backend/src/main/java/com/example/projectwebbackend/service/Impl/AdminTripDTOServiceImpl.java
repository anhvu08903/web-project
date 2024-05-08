package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminTripDTO;
import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;
import com.example.projectwebbackend.repository.SeatRepository;
import com.example.projectwebbackend.repository.TripRepository;
import com.example.projectwebbackend.service.AdminTripDTOService;
import com.example.projectwebbackend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminTripDTOServiceImpl implements AdminTripDTOService {
    @Autowired private CoachService coachService;

    @Autowired private TripRepository tripRepository;

    @Autowired private SeatRepository seatRepository;



    @Override
    public List<AdminTripDTO> getAllSeatInfo() {
      List<AdminTripDTO> adminTripDTOS = new ArrayList<>();
        List<Trip> trips = tripRepository.findAll();
        for (Trip trip : trips) {
            // Làm cái gì đó với mỗi phần tử trong trips
            AdminTripDTO adminTripDTO= new AdminTripDTO();
            adminTripDTO.setTrip(trip);
            adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
//            adminTripDTO.setAdmin(seatRepository.findAdminByCoach_Licenplate(trip.getCoach().getLicenseplate()));
            adminTripDTOS.add(adminTripDTO);
        }





        return adminTripDTOS;
    }
}
