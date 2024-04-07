package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Coach;
import org.springframework.data.repository.CrudRepository;

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
