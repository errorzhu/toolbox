package com.example.demo.service;

import com.example.demo.model.Address;

import java.util.List;
import java.util.Optional;

public interface IDemoService {

    Optional<List<Address>> getAll();
    Optional<Address> create(Address address);
}
