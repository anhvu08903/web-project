package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminTripDTO;
import com.example.projectwebbackend.dto.Admin.FilterTRip;
import com.example.projectwebbackend.entity.*;
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
            List<Ticket> tickets = ticketRepository.findAllByTripAndStatus(trip, "1");
            trip.setRemainingSeat(remainingSeat - ticketRepository.findAllByTripAndStatus(trip, "1").size());
            adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
            adminTripDTO.setPickAddress(pickAddressRepository.findAllByTrip(trip));
            adminTripDTO.setReturnAddress(returnAddressRepository.findAllByTrip(trip));
//            adminTripDTO.setSeatLocation(seatLocationRepository.findSeatLocationByLocationid(adminTripDTO.getSeat().getSeatid()));

            adminTripDTOS.add(adminTripDTO);
            tripRepository.save(trip);

        }

        return adminTripDTOS;
    }

    @Override
    public AdminTripDTO getSeatInfoByTripId(Long id) {
        AdminTripDTO adminTripDTO = new AdminTripDTO();
        Trip trip = tripRepository.findByTripid(id);
        adminTripDTO.setTrip(trip);
        adminTripDTO.setAdmin(trip.getCoach().getAdmin());

        adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
        adminTripDTO.setPickAddress(pickAddressRepository.findAllByTrip(trip));
        adminTripDTO.setReturnAddress(returnAddressRepository.findAllByTrip(trip));
//        adminTripDTO.setSeatLocation(seatLocationRepository.findSeatLocationByLocationid(adminTripDTO.getSeat().getSeatid()));
        return adminTripDTO;
    }

    @Override
    public List<AdminTripDTO> filterTrip(FilterTRip filterTRip) {
        List<AdminTripDTO> adminTripDTOS = new ArrayList<>();
        List<Trip> trips = tripRepository.findAllByStartprovinceAndEndprovince(filterTRip.getTinhDi(), filterTRip.getTinhDen());
        for (Trip trip : trips) {


            // Làm cái gì đó với mỗi phần tử trong trips
            AdminTripDTO adminTripDTO= new AdminTripDTO();
            adminTripDTO.setTrip(trip);
            adminTripDTO.setAdmin(trip.getCoach().getAdmin());
            Integer remainingSeat = trip.getCoach().getNumber();
            Admin admin = trip.getCoach().getAdmin();
            trip.setRemainingSeat(remainingSeat - ticketRepository.findAllByTripAndStatus(trip, "1").size());
            adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
             List<PickAddress> pickAddress = pickAddressRepository.findAllByPickname(filterTRip.getTinhDi().getPname());
            List<ReturnAddress> returnAddress = returnAddressRepository.findAllByReturnaddress(filterTRip.getTinhDen().getPname());
            adminTripDTO.setPickAddress(pickAddress);
            adminTripDTO.setReturnAddress(returnAddress);
            adminTripDTO.setAdmin(admin);
            adminTripDTOS.add(adminTripDTO);


        }

        return adminTripDTOS;
    }

    @Override
    public List<AdminTripDTO> getALlSeatByAdminToken(String token) {
        List<Coach> coaches = coachService.findCoachByToken(token);
        List<AdminTripDTO> adminTripDTOS = new ArrayList<>();
        for (Coach coach : coaches) {
            List<Trip> trips = tripRepository.findAllByCoach_Licenseplate(coach.getLicenseplate());
            for (Trip trip : trips) {


                // Làm cái gì đó với mỗi phần tử trong trips
                AdminTripDTO adminTripDTO = new AdminTripDTO();
                adminTripDTO.setTrip(trip);
                adminTripDTO.setAdmin(trip.getCoach().getAdmin());
                Integer remainingSeat = trip.getCoach().getNumber();
                Admin admin = trip.getCoach().getAdmin();
//                trip.setRemainingSeat(remainingSeat);
                trip.setRemainingSeat(remainingSeat - ticketRepository.findAllByTripAndStatus(trip, "1").size());
                adminTripDTO.setSeat(seatRepository.findSeatByCoach_Licenseplate(trip.getCoach().getLicenseplate()));
                adminTripDTO.setPickAddress(pickAddressRepository.findAllByTrip(trip));
                adminTripDTO.setReturnAddress(returnAddressRepository.findAllByTrip(trip));
//                adminTripDTO.setSeatLocation(seatLocationRepository.findSeatLocationByLocationid(adminTripDTO.getSeat().getSeatid()));

                adminTripDTO.setAdmin(admin);
                adminTripDTOS.add(adminTripDTO);


            }
        }

        return adminTripDTOS;
    }
}
