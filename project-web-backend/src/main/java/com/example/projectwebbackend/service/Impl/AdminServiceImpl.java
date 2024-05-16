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
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private AdminRepository adminRepository;
    //@Override
    public List<Admin> getAllAdmin() {
        return (List<Admin>) adminRepository.findAll();
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findAdminByAdminid(id);
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
    public Admin findAdminById(Long id) {
        return adminRepository.findAdminByAdminid(id);
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
        admin.setAdminaddress(request.getAdminaddress());
        String token = generateRandomString(30);
        admin.setToken(token);
        return adminRepository.save(admin);

    }

    public ResponseEntity<String> signInAdmin(String account, String password){
        Admin admin =  adminRepository.findAdminByAdminaccount(account);
        if(admin == null) {
            throw  new RuntimeException("Admin's not existed.");
        }
        if(!admin.getAdminpassword().equals(password)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        admin.setToken(generateRandomString(30));
        adminRepository.save(admin);
        return ResponseEntity.ok(admin.getToken());
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

    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    @Override
    public Admin findAdminByToken(String token) {
        return adminRepository.findAdminByToken(token);
    }
}
