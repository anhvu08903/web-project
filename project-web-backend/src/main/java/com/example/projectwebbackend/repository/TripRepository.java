package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Province;
import com.example.projectwebbackend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByStartprovinceAndEndprovince(Province sprovince, Province eprovince);

    Trip findByTripid(Long tripid);

    List<Trip> findAllByStartprovinceAndEndprovince(Province StartProvince, Province EndProvince);

    List<Trip> findAllByCoach_Licenseplate(String string);

    @Query(value = "SELECT * FROM chuyen_xe WHERE DATE(thoi_gian_di) LIKE :date", nativeQuery = true)
    List<Trip> findByDay(String date);
}
