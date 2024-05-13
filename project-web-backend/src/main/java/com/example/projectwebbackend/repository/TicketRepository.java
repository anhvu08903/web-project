package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.entity.Ticket;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findByTicketid(Long ticketid);

//    List<AdminTicketPrompt> fi();

}
