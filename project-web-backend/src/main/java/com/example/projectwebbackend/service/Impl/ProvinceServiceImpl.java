package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Province;
import com.example.projectwebbackend.repository.ProvinceRepository;
import com.example.projectwebbackend.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired private ProvinceRepository provinceRepository;
    @Override
    public List<Province> getAllProvince() {
        return (List<Province>) provinceRepository.findAll();
    }
}
