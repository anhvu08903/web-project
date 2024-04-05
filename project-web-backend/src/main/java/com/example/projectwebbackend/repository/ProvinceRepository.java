package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findProvinceByPname(String pname);
}
