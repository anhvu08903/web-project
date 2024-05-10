package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.PickAddress;
import com.example.projectwebbackend.entity.ReturnAddress;
import com.example.projectwebbackend.entity.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PickAddressRepository extends CrudRepository<PickAddress, Long> {

    PickAddress findAllByPickid(Long id);
    List<PickAddress> findAllByTrip(Trip trip);
}
