package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Province;
import com.example.projectwebbackend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByStartprovinceAndEndprovince(Province sprovince, Province eprovince);
}
