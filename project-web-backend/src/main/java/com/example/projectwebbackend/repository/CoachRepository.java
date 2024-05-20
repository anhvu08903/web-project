package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    Coach findByLicenseplate(String licenseplate);


    List<Coach> findAllByAdmin(Admin admin);

    List<Coach> findAllByAdmin_Token(String token);
}
