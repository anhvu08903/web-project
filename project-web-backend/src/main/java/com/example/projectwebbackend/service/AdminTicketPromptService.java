package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;

import java.util.List;

public interface AdminTicketPromptService {
    List<AdminTicketPrompt> getAllTicket();

    Void GrantTicket(Long id);

    Void DenyTicket(Long id);
}
