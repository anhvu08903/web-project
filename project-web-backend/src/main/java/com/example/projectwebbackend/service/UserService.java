package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.UserBookTicketRequest;
import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserPaymentRequest;
import com.example.projectwebbackend.dto.UserUpdateRequest;
import com.example.projectwebbackend.entity.*;
import com.example.projectwebbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  TripRepository tripRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private SeatLocationRepository seatLocationRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();
        if (userRepository.existsByAccount(request.getAccount())){
            throw  new RuntimeException("User existed.");
        }
        if (userRepository.existsByPhone(request.getPhone())){
            throw new RuntimeException("Phone number existed.");
        }
        user.setName(request.getName());
        user.setAccount(request.getAccount());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());

        return userRepository.save(user);

    }
    public ResponseEntity<User> signinUser(String account, String password){
        User user =  userRepository.findByAccount(account);
        if(user == null) {
            throw  new RuntimeException("User's not existed.");
        }
        if(!user.getPassword().equals(password)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updatePassword(String account, String newpassword){
        User user = userRepository.findByAccount(account);
        user.setPassword(newpassword);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public List<Trip> tripList(){
        return tripRepository.findAll();
    }

    public List<Trip> tripfilterLocation(String startpname, String endpname) {
        // Truy xuất thông tin Tỉnh từ tên
        Province sprovince = provinceRepository.findProvinceByPname(startpname);
        Province eprovince = provinceRepository.findProvinceByPname(endpname);

        if (sprovince != null && eprovince != null) {
            // Truy xuất danh sách chuyến xe dựa trên Tỉnh đi và Tỉnh đến
            return tripRepository.findByStartprovinceAndEndprovince(sprovince, eprovince);
        }
        return Collections.emptyList();
    }

    public ResponseEntity<String> bookTicket(UserBookTicketRequest request){
        Ticket ticket = new Ticket();
        ticket.setTrip(ticket.getTrip());
        ticket.setPickAddress(ticket.getPickAddress());
        ticket.setReturnAddress(ticket.getReturnAddress());
        ticket.setSeatList(ticket.getSeatList());
        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.OK).body("Them ve thanh cong");
}
    public  ResponseEntity<String> payBill(Long id, UserPaymentRequest request){
        Payment payment = new Payment();
        long totalbill = 0;
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay nguoi dung");
        }

        Ticket ticket = new Ticket();
        ticket.setTrip(request.getUserBookTicketRequest().getTrip());
        ticket.setPickAddress(request.getUserBookTicketRequest().getPickAddress());
        ticket.setReturnAddress(request.getUserBookTicketRequest().getReturnAddress());
        ticket.setSeatList(request.getUserBookTicketRequest().getSeatList());
        ticketRepository.save(ticket);

        List<Seat> seats = ticket.getSeatList();
        for (Seat seat : seats) {
            Seat seat1 = seatRepository.findBySeatid( seat.getSeatid());
            totalbill += seat1.getPrice();
        }
        payment.setUser(user);
        payment.setTicket(ticket);
        payment.setTotalprice(totalbill);
        paymentRepository.save(payment);

        return ResponseEntity.status(HttpStatus.OK).body("don dat ve thanh cong");
    }







    public List<User> getUsers(){
        return userRepository.findAll();
     }

     public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
     }

     public User updateUser(Long id, UserUpdateRequest request){
        User user = getUser(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
     }

     public void deleteUser(Long id){
        userRepository.deleteById(id);
     }
}
