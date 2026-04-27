package com.example.orders.service.impl;

import com.example.orders.mapper.AddressMapper;
import com.example.orders.pojo.Address;
import com.example.orders.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressList(Integer userId) {
        return addressMapper.findByUserId(userId);
    }
}