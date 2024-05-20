package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.repository.AdminCoachRepossitory;
import com.example.projectwebbackend.repository.AdminRepository;
import com.example.projectwebbackend.repository.CoachRepository;
import com.example.projectwebbackend.service.AdminService;
import com.example.projectwebbackend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired private AdminCoachRepossitory adminCoachRepossitory;
    @Autowired private AdminService adminService;
    @Autowired private CoachRepository coachRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Coach> getAllCoach() {
        return (List<Coach>) adminCoachRepossitory.findAll();
    }


    @Override
    public Coach  addCoachById(Long id, Coach coach) {
        Admin admin = new Admin();
        admin.setAdminid(id);
        coach.setAdmin(admin);
        return  adminCoachRepossitory.save(coach);
    }

    @Override
    public Coach addCoach(Coach coach, String token) {
        Admin admin = adminRepository.findAdminByToken(token);
        coach.setAdmin(admin);
        return adminCoachRepossitory.save(coach);
    }

    @Override
    public void deleteCoach(Long id) {
        adminCoachRepossitory.deleteById(id);
    }

    @Override
    public void saveCoach(Coach coach) {
        adminCoachRepossitory.save(coach);

    }

    @Override
    public Optional<Coach> findCoachById(Long id) {
        return adminCoachRepossitory.findById(id);
    }

    @Override
    public List<Coach> findCoachByToken(String token) {
        Admin admin1 = new Admin();
        admin1=adminService.findAdminByToken(token);

        return (List<Coach>) coachRepository.findAllByAdmin(admin1);
    }

//    @Override
//    public List<Coach> getCoachByAdinToken(String token) {
//        return List.of();
//    }

    @Override
    public List<Coach> getCoachByAdinToken(String token) {
        return  coachRepository.findAllByAdmin_Token(token);
    }

//    @Override
//    public List<Coach> findCoachByToken(String token) {
//
//        Admin admin = adminService.findAdminByToken(token);
//        List<Coach> list =
//        return (List<Coach>) coachRepository.findAllByAdmin_Token(token);
//    }
}
