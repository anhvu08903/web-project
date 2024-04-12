package com.example.projectwebbackend.repository;

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
}
