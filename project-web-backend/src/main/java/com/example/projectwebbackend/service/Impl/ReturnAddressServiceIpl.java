package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.ReturnAddress;
import com.example.projectwebbackend.repository.ReturnAddressRepository;
import com.example.projectwebbackend.service.ReturnAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReturnAddressServiceIpl implements ReturnAddressService {
    @Autowired private ReturnAddressRepository returnAddressRepository;
    @Override
    public List<ReturnAddress> getALlPickAddress() {
        return (List<ReturnAddress>) returnAddressRepository.findAll();
    }

    @Override
    public ReturnAddress addPickAdress(ReturnAddress returnAddress) {
        return returnAddressRepository.save(returnAddress);
    }

    @Override
    public void editReturnAddress(ReturnAddress returnAddress) {
                returnAddressRepository.save(returnAddress);
    }

    @Override
    public void deleteReturnAddress(Long id) {
            returnAddressRepository.deleteById(id);
    }
}
