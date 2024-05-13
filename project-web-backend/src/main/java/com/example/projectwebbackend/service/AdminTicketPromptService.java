package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.entity.Ticket;

import java.util.List;

public interface AdminTicketPromptService {
    List<Ticket> getAllTicket();

    Void GrantTicket(Long id);

    Void DenyTicket(Long id);
}
