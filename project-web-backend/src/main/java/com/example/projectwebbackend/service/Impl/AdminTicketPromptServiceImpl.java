package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.entity.Ticket;
import com.example.projectwebbackend.repository.TicketRepository;
import com.example.projectwebbackend.service.AdminTicketPromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminTicketPromptServiceImpl implements AdminTicketPromptService {
    @Autowired private TicketRepository ticketRepository;
    @Override
    public List<Ticket> getAllTicket() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public Void GrantTicket(Long id) {
        return null;
    }

    @Override
    public Void DenyTicket(Long id) {
        return null;
    }
}
