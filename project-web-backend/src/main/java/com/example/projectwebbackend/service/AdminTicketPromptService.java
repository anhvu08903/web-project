package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminTicketPromptService {
    List<Ticket> getAllTicket();

    List<Ticket> getTicketByTripId(Long id);

    ResponseEntity<?> GrantTicket(Long id);

    Void DenyTicket(Long id);
}
