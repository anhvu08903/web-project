package com.example.projectwebbackend.controllers.admin;

import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.repository.AdminCoachRepossitory;
import com.example.projectwebbackend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/coach")
public class CoachController {
    @Autowired private CoachService coachService;
    @Autowired private AdminCoachRepossitory coachRepossitory;

    List<Coach> coaches = new ArrayList<>();

    @GetMapping
    public List<Coach> getAllCoach(){
        return coachService.getAllCoach();
    }

    @PostMapping("/add/{id}")
    public Coach addCoach(@RequestParam Long id, @RequestBody Coach coach){
        return coachService.addCoach(id, coach);
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
}
