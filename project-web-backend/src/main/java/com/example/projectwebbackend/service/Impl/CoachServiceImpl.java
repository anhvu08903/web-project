package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.repository.CoachRepossitory;
import com.example.projectwebbackend.service.AdminService;
import com.example.projectwebbackend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired private CoachRepossitory coachRepossitory;
    @Autowired private AdminService adminService;
    @Override
    public List<Coach> getAllCoach() {
        return (List<Coach>) coachRepossitory.findAll();
    }

    @Override
    public Coach  addCoach(Long id, Coach coach) {
        Admin admin = new Admin();
        admin.setAdminid(id);
        coach.setAdmin(admin);
        return  coachRepossitory.save(coach);
    }

    @Override
    public void deleteCoach(Long id) {
        coachRepossitory.deleteById(id);
    }

    @Override
    public void saveCoach(Coach coach) {
        coachRepossitory.save(coach);

    }

    @Override
    public Optional<Coach> findCoachById(Long id) {
        return coachRepossitory.findById(id);
    }
}
