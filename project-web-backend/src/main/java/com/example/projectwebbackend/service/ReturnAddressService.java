package com.example.projectwebbackend.service;

import com.example.projectwebbackend.entity.ReturnAddress;

import java.util.List;

public interface ReturnAddressService {

    List<ReturnAddress> getALlPickAddress();

    ReturnAddress addPickAdress(ReturnAddress returnAddress);

    void editReturnAddress(ReturnAddress returnAddress);

    void deleteReturnAddress(Long id);
}
