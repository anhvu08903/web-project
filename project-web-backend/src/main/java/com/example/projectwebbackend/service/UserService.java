package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.*;
import com.example.projectwebbackend.entity.*;
import com.example.projectwebbackend.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;
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
    @Autowired
    private CommentRepository commentRepository;
    @Autowired private PickAddressRepository pickAddressRepository;

    @Autowired private ReturnAddressRepository returnAddressRepository;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByAccount(request.getAccount())) {
            throw new RuntimeException("User existed.");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number existed.");
        }
        User user = new User();
        user.setName(request.getName());
        user.setAccount(request.getAccount());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());

        return userRepository.save(user);

    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public ResponseEntity<String> signinUser(String account, String password) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new RuntimeException("User's not existed.");
        }
        if (!user.getPassword().equals(password))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        String token = generateRandomString(30);
        user.setToken(token);
        userRepository.save(user);
        return ResponseEntity.ok(token);
    }

    public User getUserbyToken(String token) {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new RuntimeException("User's not existed.");
        }
        return user;
    }

    public ResponseEntity<User> updatePassword(String account, String newpassword, String renewpassword) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new RuntimeException("User's not existed.");
        }
        if (!newpassword.equals(renewpassword)) {
            throw new RuntimeException("Nhap lai mat khau moi khong dung.");
        } else {
            user.setPassword(newpassword);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    public ResponseEntity<Comment> createComment(UserCommentationRequest request) {
        try {
            Comment comment = new Comment();
            comment.setUser(request.getUser());
            comment.setContent(request.getContent());
            comment.setCreatedAt(request.getCreatedAt());
            commentRepository.save(comment);
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<Seat>> getAllSeatsOnCoach(String licenseplate) {
        Coach coach = coachRepository.findByLicenseplate(licenseplate);
        if (coach != null) {
            List<Seat> seats = seatRepository.findAllByCoach(coach);
            if (!seats.isEmpty()) {
                return new ResponseEntity<>(seats, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Seat> addSeat(SeatDto dto) {
        try {
            Seat seat = new Seat();
            seat.setPrice(dto.getPrice());
            seat.setSeatLocation(dto.getSeatLocation());
            seat.setCoach(dto.getCoach());
            seat.setType(dto.getType());

            seatRepository.save(seat);
            return ResponseEntity.status(HttpStatus.OK).body(seat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public List<Trip> tripList() {
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

    public ResponseEntity<String> bookTicket(UserTicketBookingRequest request) {
        Ticket ticket = new Ticket();
        Trip trip = tripRepository.findByTripid(request.getTripid());
        if (trip == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ticket.setTrip(trip);
        PickAddress pickAddress = pickAddressRepository.findByPickname(request.getPickAddress().getPickname());
        ReturnAddress returnAddress = returnAddressRepository.findByReturnaddress(request.getReturnAddress().getReturnaddress());
        ticket.setPickAddress(pickAddress);
        ticket.setReturnAddress(returnAddress);
        ticket.setSeatlocation(request.getSeatlocation());
        String seatlocation = request.getSeatlocation();
        String status = request.getStatus();
        ticket.setStatus(status);
        ticket.setSeatlocation(seatlocation);

        List<Long> seatids = request.getSeatid();
        List<Seat> seats = new ArrayList<>();
        for (Long seatid : seatids) {
            Seat seat2 = seatRepository.findBySeatid(seatid);
            if (seat2 != null) {
                seats.add(seat2);
                ticket.setSeatList(seats);
                ticketRepository.save(ticket);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay ghe");
            }
        }
//        ticket.setSeatList(seats);
//        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.OK).body("Them ve thanh cong");
    }

    public ResponseEntity<String> payBill(String token, UserPaymentRequest request) {
        Payment payment = new Payment();
        long totalbill = 0;
        User user = userRepository.findByToken(token);
        if (user == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay nguoi dung");
        }

        Ticket ticket = ticketRepository.findByTicketid(request.getTicketid());
        if (ticket == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay ve");
        }

        List<Seat> seats = ticket.getSeatList();
        for (Seat seat : seats) {
            Seat seat1 = seatRepository.findBySeatid(seat.getSeatid());
            totalbill += seat1.getPrice();
        }
        payment.setUser(user);
        payment.setTicket(ticket);
        payment.setTotalprice(totalbill);
        paymentRepository.save(payment);

        return ResponseEntity.status(HttpStatus.OK).body("don thanh toan hien thi thanh cong");
    }

    public ResponseEntity<List<Payment>> getAllTicketBookings(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Payment> payments = paymentRepository.findPaymentsByUser(user);
            if (!payments.isEmpty()) {
                return ResponseEntity.ok(payments);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, UserUpdateRequest request) {
        User user = getUser(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
