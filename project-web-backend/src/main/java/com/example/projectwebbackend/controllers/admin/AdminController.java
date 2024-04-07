package com.example.projectwebbackend.controllers.admin;
import com.example.projectwebbackend.dto.AdminCoach;
import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.repository.AdminRepository;
import com.example.projectwebbackend.repository.AdminCoachRepossitory;
import com.example.projectwebbackend.service.AdminService;
import com.example.projectwebbackend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    AdminRepository adminRepository;

    @Autowired private CoachService coachService;
    @Autowired private AdminCoachRepossitory adminCoachRepossitory;




    private List<Admin> admins = new ArrayList<>();

    @GetMapping
    //public List<Admin> adminIndex(Model model) {
        //admins = adminService.getAllAdmin();
        //return admins;
   // }

    //@PostMapping("/all")
    //public List<Admin> addAllAdmin(@RequestBody List<Admin> admins) {
//        admins.add(admin);

        //adminRepository.saveAll(admins);
        //return adminService.getAllAdmin();
    //}

    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admins) {
        return adminService.addAdmin(admins);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // Kiểm tra xem người dùng có tồn tại không
        Admin existingUser = adminRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Xóa người dùng từ cơ sở dữ liệu
        adminRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminUpdate) {

        // kiem tra ton tai
        Admin existedAdmin = adminRepository.findById(id).orElse(null);


        if(existedAdmin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existedAdmin.setAdminid(id);
        existedAdmin.setAdminaccount(adminUpdate.getAdminaccount());
        existedAdmin.setAdminaddress(adminUpdate.getAdminaddress());
        existedAdmin.setAdminemail(adminUpdate.getAdminemail());
        existedAdmin.setAdminpassword(adminUpdate.getAdminpassword());
        existedAdmin.setAdminname(adminUpdate.getAdminname());
        existedAdmin.setAdminphone(adminUpdate.getAdminphone());
        adminRepository.save(existedAdmin);
        return new ResponseEntity<>(existedAdmin, HttpStatus.OK);
    }

    @PostMapping("/add/coach/{id}")
    public ResponseEntity<AdminCoach> addCoach(@PathVariable Long id, @RequestBody Coach coach) {
        AdminCoach adminCoach = new AdminCoach();

        adminCoach.setAdmin(adminService.findAdminById(id).orElse(null));
        if (adminCoach == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adminCoach.setCoach(coach);

        coachService.saveCoach(adminCoach.getCoach());

        return new ResponseEntity<>(adminCoach, HttpStatus.CREATED);
    }


}


