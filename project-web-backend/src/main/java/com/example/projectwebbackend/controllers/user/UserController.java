package com.example.projectwebbackend.controllers.user;

import com.example.projectwebbackend.dto.*;
import com.example.projectwebbackend.entity.*;
import com.example.projectwebbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/dangky")
    User createUser(@RequestBody @Valid UserCreationRequest request) {

        return userService.createUser(request);
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<User> signinUser(@RequestBody @Valid UserSigninRequest request){
        return userService.signinUser(request.getAccount(), request.getPassword());
    }

    @PutMapping("/thaydoimatkhau")
    public ResponseEntity<User> updatePassword(@RequestParam String account, @RequestParam String newpassword) {
        return userService.updatePassword(account, newpassword);
    }
    @PostMapping("/binhluan")
    public ResponseEntity<Comment> createComment(@RequestBody @Valid UserCommentationRequest request){
        return userService.createComment(request);
    }

    @GetMapping("/danhsachtatcachuyenxe")
    public List<Trip> tripList() {
        return userService.tripList();
    }

    @GetMapping("/danhsachtatcacacghetrenchuyenxe")
    public ResponseEntity<List<Seat>> getAllSeatsOnCoach(@PathVariable String licenseplate ) {
        return userService.getAllSeatsOnCoach(licenseplate);
    }
    @GetMapping("/danhsachchuyenxetheodiemdiden")
    public List<Trip> tripfilterLocation(@RequestParam String startpname, @RequestParam String endpname) {
        return userService.tripfilterLocation(startpname, endpname);
    }

    @PostMapping("/datvexe")
    public ResponseEntity<String> bookTicket(@RequestBody UserTicketBookingRequest request){
        return userService.bookTicket(request);
    }

    @PostMapping("/thanhtoan/{id}")
    public  ResponseEntity<String> payBill(@PathVariable Long id, @RequestBody UserPaymentRequest request){
        return userService.payBill(id, request);
    }

    @GetMapping("/lichsudatve/{id}")
    public  ResponseEntity<List<Payment>> getAllTicketBookings(@PathVariable Long id){
        return  userService.getAllTicketBookings(id);
    }

    @GetMapping("/taikhoannguoidung")
    public  List<User> getUsers(){
         return userService.getUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable long id, @RequestBody UserUpdateRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User has been deleted";
    }
}
