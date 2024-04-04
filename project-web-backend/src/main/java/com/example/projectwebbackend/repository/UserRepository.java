package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByAccount(String account);
    boolean existsByPhone(String phone);

    User findByAccount(String account);


}
