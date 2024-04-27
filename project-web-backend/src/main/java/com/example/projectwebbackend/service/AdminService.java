package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.Admin.AdminSignInRequest;
import com.example.projectwebbackend.dto.Admin.AdminSignUpRequest;
import com.example.projectwebbackend.entity.Admin;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Admin> getAllAdmin();

    void saveAdmin(Admin admin);

    void deleteAdmin(Long id);

    Optional<Admin> findAdminById(Long id);

    Admin addAdmin(Admin admins);


    Admin adminSignUp(AdminSignUpRequest request);

    ResponseEntity<Admin> signInAdmin(String account, String password);
    ResponseEntity<Admin> updatePassword(String account, String newpassword);
}
