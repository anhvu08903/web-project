package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.repository.AdminRepository;
import com.example.projectwebbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private AdminRepository adminRepository;
    //@Override
    public List<Admin> getAllAdmin() {
        return (List<Admin>) adminRepository.findAll();
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Optional<Admin> findAdminById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin addAdmin(Admin admins) {
        return adminRepository.save(admins);
    }
}
