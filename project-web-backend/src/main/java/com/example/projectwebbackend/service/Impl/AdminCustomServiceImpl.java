package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.service.AdminCustomService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.util.List;

public class AdminCustomServiceImpl implements AdminCustomService {

    @Autowired private EntityManager entityManager;
    @Override
    public List<Admin> getAllAdmin() {
        String query = "SELECT * From Admin";
        Query query1 = entityManager.createQuery(query);
        return null;
    }
}
