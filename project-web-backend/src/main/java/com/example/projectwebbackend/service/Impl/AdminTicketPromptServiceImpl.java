package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.service.AdminTicketPromptService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminTicketPromptServiceImpl implements AdminTicketPromptService {
    @Override
    public List<AdminTicketPrompt> getAllTicket() {
        return null;
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
