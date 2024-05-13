package com.example.projectwebbackend.controllers.admin;
import com.example.projectwebbackend.dto.Admin.*;
import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserSigninRequest;
import com.example.projectwebbackend.entity.*;
import com.example.projectwebbackend.repository.*;
import com.example.projectwebbackend.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
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

    @Autowired private CoachRepository coachRepository;

    @Autowired private AdminTripDTOService adminTripDTOService;

    @Autowired private ProvinceService provinceService;

    @Autowired private ReturnAddressService returnAddressService;

    @Autowired private PickAddressService pickAddressService;

    @Autowired private PickAddressRepository pickAddressRepository;

    @Autowired private SeatRepository seatRepository;

    @Autowired private CommentService commentService;




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
//        String adminAccount = request.getAdminaccount();
//        String adminPassword = request.getAdminpassword();
        // Kiểm tra thông tin đăng nhập của admin và tạo token
//        if (adminAccount.equals("admin") && adminPassword.equals("adminpassword")) {
//            String token = tokenService.generateToken(adminAccount);
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
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

        adminCoach.setAdmin(adminService.findAdminById(id));
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

//Coach coach = new Coach();
//coach=coachRepository.findByLicenseplate(trip.getCoach().getLicenseplate());
//trip.setCoach(coach);
        tripRepository.save(trip);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    //danh sach cac ve dang cho
    @GetMapping("/ticket")
    public List<Ticket> getAllTicket(){
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


        @GetMapping("/trip")
        public List<Trip> getAllTrip(){
            List<Trip> trips = tripRepository.findAll();
            System.out.println(trips);

        return  trips;
    }
    //

    @GetMapping("/coach/{licenseplate}")
    public Coach getByLicenseplate(@PathVariable String licenseplate ){
        return  coachRepository.findByLicenseplate(licenseplate);
    }


    //lay tat ca thong tin chuyen di ghe ngoi gia ve
    @GetMapping("tripseat")
    public List<AdminTripDTO> getAllTripInfo(){
            List<AdminTripDTO> adminTripDTOS = adminTripDTOService.getAllSeatInfo();
        return  adminTripDTOService.getAllSeatInfo();

    }

    @GetMapping("/province")
    public List<Province> getAllProvinnce(){
            return provinceService.getAllProvince();
    }

    @GetMapping("/pickaddress")
    public List<PickAddress> getAllPickAddress(){
            return pickAddressService.getALlPickAddress();
    }

    @GetMapping("/returnaddress")
    public List<ReturnAddress> getAllReturnAddress(){
        return returnAddressService.getALlReturnAddress();
    }

    @DeleteMapping("/pickaddress/{id}")
    public ResponseEntity<String> deletePickAddress(@PathVariable Long id) {
        // Kiem tra xem co ton tai nha xe khong
        PickAddress existing = pickAddressRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PickAddress not found");
        }


        pickAddressService.deletePickAddress(id);

        return ResponseEntity.status(HttpStatus.OK).body("PickAddress deleted successfully");
    }

    @PostMapping("/pickaddress/update/{id}")
    public ResponseEntity<String> editPickAddress(@PathVariable Long id, @RequestBody PickAddress pickAddress) {

        pickAddressService.editPickAddress(pickAddress);
        return ResponseEntity.status(HttpStatus.OK).body("PickAddress deleted successfully");
    }

    @GetMapping ("/pickaddress{id}")
    public PickAddress getPickAddressById(Long id){
            return pickAddressService.findPickAddressByIs(id);
    }


    @GetMapping("/seat")
    public List<Seat> getAllSeat(){
            return (List<Seat>) seatRepository.findAll();
    }

    @GetMapping("/comment")
    public List<Comment> getAllComment(){

            return commentService.getAllComment();
    }

    @GetMapping("/comment/{id}")
    public List<Comment> getAllCommentByAdminId(@PathVariable Long id){

        return commentService.getCommentByAdminId(id);
    }

@GetMapping("/comment/rate/{id}")
    public Double rateComment(@PathVariable Long id){
            List<Comment> comments = commentService.getCommentByAdminId(id);

    if (comments == null || comments.isEmpty()) {
        return 0.0;
    }

    int totalStars = 0;
    int numberOfRatings = 0;
    for (Comment comment : comments) {
        String star = comment.getStar();
        if (star != null && !star.isEmpty()) {
            totalStars += Integer.parseInt(star);
            numberOfRatings++;
        }
    }

    if (numberOfRatings == 0) {
        return 0.0;
    }

    return (double) totalStars / numberOfRatings;
}

}


