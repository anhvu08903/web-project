package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminTripDTO;
import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.Seat;
import com.example.projectwebbackend.entity.Trip;
import com.example.projectwebbackend.repository.*;
import com.example.projectwebbackend.service.AdminService;
import com.example.projectwebbackend.service.AdminTripDTOService;
import com.example.projectwebbackend.service.CoachService;
import com.example.projectwebbackend.service.SeatService;
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

    @Autowired private AdminService adminService;

    @Autowired private SeatService seatService;

    @Autowired private PickAddressRepository pickAddressRepository;

    @Autowired private ReturnAddressRepository returnAddressRepository;

    @Autowired private SeatLocationRepository seatLocationRepository;

    @Autowired private TicketRepository ticketRepository;


    @Override
    public List<AdminTripDTO> getAllSeatInfo() {
        List<AdminTripDTO> adminTripDTOS = new ArrayList<>();
        List<Trip> trips = tripRepository.findAll();
        for (Trip trip : trips) {


            // Làm cái gì đó với mỗi phần tử trong trips
            AdminTripDTO adminTripDTO= new AdminTripDTO();
            adminTripDTO.setTrip(trip);
            adminTripDTO.setAdmin(trip.getCoach().getAdmin());
            Integer remainingSeat = trip.getCoach().getNumber();
            trip.setRemainigSeat(remainingSeat-ticketRepository.findAllByStatus("1").size());            adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
            adminTripDTO.setPickAddress(pickAddressRepository.findAllByTrip(trip));
            adminTripDTO.setReturnAddress(returnAddressRepository.findAllByTrip(trip));
            adminTripDTO.setSeatLocation(seatLocationRepository.findSeatLocationByLocationid(adminTripDTO.getSeat().getSeatid()));

            adminTripDTOS.add(adminTripDTO);


        }

        return adminTripDTOS;
    }

    @Override
    public AdminTripDTO getSeatInfoByTripId(Long id) {
        AdminTripDTO adminTripDTO = new AdminTripDTO();
        Trip trip = tripRepository.findByTripid(id);
        adminTripDTO.setTrip(trip);
        adminTripDTO.setAdmin(trip.getCoach().getAdmin());
        Integer remainingSeat = trip.getCoach().getNumber();
        adminTripDTO.setRemainingSeat(remainingSeat);
        adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
        adminTripDTO.setPickAddress(pickAddressRepository.findAllByTrip(trip));
        adminTripDTO.setReturnAddress(returnAddressRepository.findAllByTrip(trip));
        adminTripDTO.setSeatLocation(seatLocationRepository.findSeatLocationByLocationid(adminTripDTO.getSeat().getSeatid()));
        return adminTripDTO;
    }
}
