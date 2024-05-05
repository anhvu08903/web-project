package com.example.projectwebbackend.controllers.admin;
import com.example.projectwebbackend.dto.Admin.AdminCoach;
import com.example.projectwebbackend.dto.Admin.AdminSignInRequest;
import com.example.projectwebbackend.dto.Admin.AdminSignUpRequest;
import com.example.projectwebbackend.dto.Admin.AdminTicketPrompt;
import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserSigninRequest;
import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.Trip;
import com.example.projectwebbackend.entity.User;
import com.example.projectwebbackend.repository.AdminRepository;
import com.example.projectwebbackend.repository.AdminCoachRepossitory;
import com.example.projectwebbackend.repository.TripRepository;
import com.example.projectwebbackend.service.AdminService;
import com.example.projectwebbackend.service.AdminTicketPromptService;
import com.example.projectwebbackend.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    AdminRepository adminRepository;

    @Autowired private CoachService coachService;
    @Autowired private AdminCoachRepossitory adminCoachRepossitory;

    @Autowired private TripRepository tripRepository;

    @Autowired private AdminTicketPromptService adminTicketPromptService;




    private List<Admin> admins = new ArrayList<>();


    //danh sach tat ca nha xe
        @GetMapping
        public List<Admin> getAllAdmins(){
            return  adminService.getAllAdmin();

        }

        //dang ki
    @PostMapping("/signup")
    Admin signUpAdmin(@RequestBody @Valid AdminSignUpRequest request) {

        return adminService.adminSignUp(request);
    }

   // dang nhap
    @PostMapping("/signin")
    public ResponseEntity<Admin> signinAdmin(@RequestBody @Valid AdminSignInRequest request){
        return adminService.signInAdmin(request.getAdminaccount(), request.getAdminpassword());

    }


        //them tai khoan admin
    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admins) {
        return adminService.addAdmin(admins);
    }

    //xoa tai khoan
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        // Kiem tra xem co ton tai nha xe khong
        Admin existingUser = adminRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }


        adminRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    //cap nhat thong tin nguoi dung
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

    //sua thong tin chuyen xe
    @PostMapping("/update/coach/{id}")
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
    //them chuyen xe cho nha xe(id)
    @PostMapping("/add/trip")
    public ResponseEntity<Trip> addTrip( @RequestBody Trip trip){

        tripRepository.save(trip);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    //danh sach cac ve dang cho
    @GetMapping("/ticket")
    public List<AdminTicketPrompt> getAllTicket(){
            return adminTicketPromptService.getAllTicket();
    }
    // chap nhan yeu cau dat ve
    @PostMapping("ticket/grant/{id}")
    public void grantTicket(@PathVariable Long  id){
            adminTicketPromptService.GrantTicket(id);
    }

    //tu choi yeu cau dat ve
    @PostMapping("ticket/deny/{id}")
    public void denyTicket(@PathVariable Long  id){
        adminTicketPromptService.DenyTicket(id);
    }


//    @GetMapping
//    public List<Trip> getAllTrip(){
//        return  tripRepository.get();
//
//    }
}


