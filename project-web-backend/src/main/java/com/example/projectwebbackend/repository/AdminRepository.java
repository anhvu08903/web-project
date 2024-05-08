package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    boolean existsAdminByAdminaccount(String AdminAccount);

    boolean existsAdminByAdminphone(String AdminPhone);

    Admin findAdminByAdminaccount(String admin);
    Admin findAdminByAdminid(Long id);
}
