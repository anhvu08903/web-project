package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.dto.Admin.AdminSignUpRequest;
import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.User;
import com.example.projectwebbackend.repository.AdminRepository;
import com.example.projectwebbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Admin adminSignUp(AdminSignUpRequest request){
        Admin admin = new Admin();
        if (adminRepository.existsAdminByAdminaccount(request.getAdminaccount())){
            throw  new RuntimeException("Admin existed.");
        }
        if (adminRepository.existsAdminByAdminphone(request.getAdminphone())){
            throw new RuntimeException("Phone number existed.");
        }
        admin.setAdminname(request.getAdminname());
        admin.setAdminaccount(request.getAdminaccount());
        admin.setAdminpassword(request.getAdminpassword());
        admin.setAdminphone(request.getAdminphone());
            admin.setAdminemail(request.getAdminemail());

        return adminRepository.save(admin);

    }

    public ResponseEntity<Admin> signInAdmin(String account, String password){
        Admin admin =  adminRepository.findAdminByAdminaccount(account);
        if(admin == null) {
            throw  new RuntimeException("Admin's not existed.");
        }
        if(!admin.getAdminpassword().equals(password)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        return ResponseEntity.ok(admin);
    }

    public ResponseEntity<Admin> updatePassword(String account, String newpassword){
        Admin admin = adminRepository.findAdminByAdminaccount(account);
        if(admin == null) {
            throw  new RuntimeException("Admin's not existed.");
        }
        admin.setAdminpassword(newpassword);
        adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }
}
