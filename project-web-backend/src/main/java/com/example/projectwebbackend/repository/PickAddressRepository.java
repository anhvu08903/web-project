package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.PickAddress;
import org.springframework.data.repository.CrudRepository;

public interface PickAddressRepository extends CrudRepository<PickAddress, Long> {

    PickAddress findAllByPickid(Long id);
}
