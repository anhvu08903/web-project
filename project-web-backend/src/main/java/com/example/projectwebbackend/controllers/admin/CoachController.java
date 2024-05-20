package com.example.projectwebbackend.controllers.admin;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.repository.AdminCoachRepossitory;
import com.example.projectwebbackend.repository.AdminRepository;
import com.example.projectwebbackend.repository.SeatRepository;
import com.example.projectwebbackend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/coach")
@CrossOrigin
public class CoachController {
    @Autowired private CoachService coachService;
    @Autowired private AdminCoachRepossitory coachRepossitory;
    @Autowired private SeatRepository seatRepository;

    List<Coach> coaches = new ArrayList<>();
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public List<Coach> getAllCoach(){
        return coachService.getAllCoach();
    }

//    them theo id nhaxe
    @PostMapping("/add/{id}")
    public Coach addCoachById(@RequestParam Long id, @RequestBody Coach coach){
        return coachService.addCoachById(id, coach);
    }

//them xe
    @PostMapping("/add/{token}")
    public Coach addCoach(@RequestBody Coach coach, @PathVariable String token){
        return  coachService.addCoach(coach, token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // Kiểm tra xem người dùng có tồn tại không
        Coach exsistingCoach = coachRepossitory.findById(id).orElse(null);
        if (exsistingCoach == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Xóa người dùng từ cơ sở dữ liệu
        coachRepossitory.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateAdmin(@PathVariable Long id, @RequestBody Coach coachUpdate) {
        Coach exsistingCoach = coachRepossitory.findById(id).orElse(null);
        if(exsistingCoach == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        exsistingCoach.setCoachtype(coachUpdate.getCoachtype());
        exsistingCoach.setLicenseplate(coachUpdate.getLicenseplate());
        exsistingCoach.setNumber(coachUpdate.getNumber());
        return new ResponseEntity<>(exsistingCoach, HttpStatus.OK);

    }
//Chien sua
    @GetMapping("/{token}")
    public List<Coach> getCoachByToken(@PathVariable String token){
        return coachService.findCoachByToken(token);
    }

    @GetMapping("/admin/{token}")
    public List<Coach> getCoachByAdminToken(@PathVariable String token){
        return coachService.findCoachByToken(token);
    }
}
