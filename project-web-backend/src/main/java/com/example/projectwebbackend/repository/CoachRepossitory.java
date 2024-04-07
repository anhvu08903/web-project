package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Coach;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CoachRepossitory extends CrudRepository<Coach, Long> {
//    @Modifying
//    @Transactional
//    @Query("INSERT INTO Coach(licenseplate, coachtype, number, admin) " +
//            "SELECT :licenseplate, :coachtype, :number, a FROM Admin a WHERE a.adminid = :adminid")
//    void addCoachWithAdminId(@Param("licenseplate") String licenseplate,
//                             @Param("coachtype") String coachtype,
//                             @Param("number") Integer number,
//                             @Param("adminid") Long adminid);
}
