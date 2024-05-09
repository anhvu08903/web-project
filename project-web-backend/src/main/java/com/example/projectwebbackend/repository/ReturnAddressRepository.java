package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.ReturnAddress;
import com.example.projectwebbackend.entity.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReturnAddressRepository extends CrudRepository<ReturnAddress, Long> {
    List<ReturnAddress> findAllByTrip(Trip trip);
}
