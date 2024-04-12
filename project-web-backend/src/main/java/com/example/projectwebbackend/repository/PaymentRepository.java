package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Payment;
import com.example.projectwebbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentsByUser(User user);
}
