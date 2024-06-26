package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.entity.Ticket;
import com.example.projectwebbackend.entity.Trip;
import com.example.projectwebbackend.repository.TicketRepository;
import com.example.projectwebbackend.repository.TripRepository;
import com.example.projectwebbackend.service.AdminTicketPromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminTicketPromptServiceImpl implements AdminTicketPromptService {
    @Autowired private TicketRepository ticketRepository;

    @Autowired private TripRepository tripRepository;
    @Override
    public List<Ticket> getAllTicket() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketByTripId(Long id) {
        Trip trip = tripRepository.findByTripid(id);
        return ticketRepository.findAllByTrip(trip);
    }

    @Override
    public ResponseEntity<?> GrantTicket(Long id) {
        Ticket ticket = ticketRepository.findByTicketid(id);
        ticket.setStatus("1");
        ticketRepository.save(ticket);
        return ResponseEntity.ok("Ve da duoc dat");
    }

    @Override
    public Void DenyTicket(Long id) {
        return null;
    }
}
