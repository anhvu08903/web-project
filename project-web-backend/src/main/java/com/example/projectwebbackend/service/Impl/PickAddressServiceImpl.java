package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.PickAddress;
import com.example.projectwebbackend.repository.PickAddressRepository;
import com.example.projectwebbackend.service.PickAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PickAddressServiceImpl implements PickAddressService {
    @Autowired private PickAddressRepository pickAddressRepository;
    @Override
    public List<PickAddress> getALlPickAddress() {
        return (List<PickAddress>) pickAddressRepository.findAll();
    }

    @Override
    public PickAddress addPickAdress(PickAddress pickAddress) {
        return pickAddressRepository.save(pickAddress);
    }

    @Override
    public void editPickAddress(PickAddress pickAddress) {
            pickAddressRepository.save(pickAddress);
    }

    @Override
    public PickAddress findPickAddressByIs(Long id) {
        return pickAddressRepository.findAllByPickid(id);
    }

    @Override
    public void deletePickAddress(Long id) {
        pickAddressRepository.deleteById(id);

    }
}
