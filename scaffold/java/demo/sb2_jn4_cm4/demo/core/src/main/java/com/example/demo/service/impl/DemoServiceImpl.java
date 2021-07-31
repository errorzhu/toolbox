package com.example.demo.service.impl;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Optional<List<Address>> getAll() {
        return Optional.of(addressRepository.findAll());
    }

    @Override
    public Optional<Address> create(Address address) {
        addressRepository.save(address);
        return Optional.of(address);
    }


}
