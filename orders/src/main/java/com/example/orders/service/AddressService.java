package com.example.orders.service;

import com.example.orders.pojo.Address;
import java.util.List;

public interface AddressService {
    List<Address> getAddressList(Integer userId);
}