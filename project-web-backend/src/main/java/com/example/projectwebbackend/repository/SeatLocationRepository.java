package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.SeatLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SeatLocationRepository extends JpaRepository<SeatLocation,Long> {
    SeatLocation findSeatLocationByLocationid(Long locationid);
}
