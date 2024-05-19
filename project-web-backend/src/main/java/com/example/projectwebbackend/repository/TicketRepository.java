package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.entity.Ticket;
import com.example.projectwebbackend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findByTicketid(Long ticketid);

    List<Ticket> findAllByStatus(String status);

    List<Ticket> findAllByTrip(Trip trip);

    List<Ticket> findAllByTripAndStatus(Trip trip, String status);
    //    List<AdminTicketPrompt> fi();

}
