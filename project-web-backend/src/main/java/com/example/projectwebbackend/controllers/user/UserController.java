package com.example.projectwebbackend.controllers.user;

import com.example.projectwebbackend.dto.*;
import com.example.projectwebbackend.entity.*;
import com.example.projectwebbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/dangky")
    User createUser(@RequestBody @Valid UserCreationRequest request) {

        return userService.createUser(request);
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<String> signinUser(@RequestBody @Valid UserSigninRequest request){
        return userService.signinUser(request.getAccount(), request.getPassword());
    }

    @PutMapping("/thaydoimatkhau")
    public ResponseEntity<User> updatePassword(@RequestParam String account, @RequestParam String newpassword, @RequestParam String renewpassword) {
        return userService.updatePassword(account, newpassword, renewpassword);
    }
    @PostMapping("/binhluan")
    public ResponseEntity<Comment> createComment(@RequestBody @Valid UserCommentationRequest request){
        return userService.createComment(request);
    }

    @GetMapping("/danhsachtatcachuyenxe")
    public List<Trip> tripList() {
        return userService.tripList();
    }
    @PostMapping("/themghe")
    public ResponseEntity<Seat> addSeat(@RequestBody SeatDto dto){
        return userService.addSeat(dto);
    }
    @GetMapping("/danhsachtatcacacghetrenchuyenxe/{licenseplate}")
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

    @PostMapping("/thanhtoan")
    public ResponseEntity<String> payBill(HttpServletRequest httpServletRequest, @RequestBody UserPaymentRequest request) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring(0);
                return userService.payBill(token, request);
            } else {
                // Token không hợp lệ, trả về lỗi 401 Unauthorized
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found or invalid");
            }
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
