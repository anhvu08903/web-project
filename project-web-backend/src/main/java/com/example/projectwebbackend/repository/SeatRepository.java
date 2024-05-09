package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    boolean existsBySeatid(Long seatid);

    Seat findBySeatid(Long seatid);
    List<Seat> findAllByCoach(Coach coach);

    Seat findSeatByCoach_Licenseplate(String licenseplate);

<<<<<<< HEAD
//
//    Admin findAdminByCoach_Licenplate(String licenseplate);
//
//    Admin fi

=======
>>>>>>> f0934548f125fa291c7d26e548dc03a0d544a846

}
