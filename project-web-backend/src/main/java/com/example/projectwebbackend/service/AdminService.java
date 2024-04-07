package com.example.projectwebbackend.service;

import com.example.projectwebbackend.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Admin> getAllAdmin();

    void saveAdmin(Admin admin);

    void deleteAdmin(Long id);

    Optional<Admin> findAdminById(Long id);

    Admin addAdmin(Admin admins);


}
