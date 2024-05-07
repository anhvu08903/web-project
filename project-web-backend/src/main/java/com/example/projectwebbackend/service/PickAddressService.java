package com.example.projectwebbackend.service;

import com.example.projectwebbackend.entity.PickAddress;

import java.util.List;

public interface PickAddressService {
    List<PickAddress> getALlPickAddress();

    PickAddress addPickAdress(PickAddress pickAddress);

    void editPickAddress(PickAddress pickAddress);

    PickAddress findPickAddressByIs(Long id);

    void deletePickAddress(Long id);
}
