package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface PaymentRepository extends JpaRepository<Payment, Long> {
}
