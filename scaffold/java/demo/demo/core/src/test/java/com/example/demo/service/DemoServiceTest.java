package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class DemoServiceTest {

    @Resource
    IDemoService iDemoService;

    @MockBean
    AddressRepository addressRepository;


    @Test
    public void test_address_add() {
        Address address = new Address();
        address.setId(3L);
        address.setCity("杭州");
        address.setUserId(3L);
        address.setProvince("浙江");
        address.setStreet("颐和路");
        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        Optional<Address> optionalAddress = iDemoService.create(address);
        Assertions.assertThat(address).isEqualTo(optionalAddress.get());


    }

    @Test
    public void test_address_list(){
        Address address = new Address();
        address.setId(3L);
        address.setCity("杭州");
        address.setUserId(3L);
        address.setProvince("浙江");
        address.setStreet("颐和路");

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        Mockito.when(addressRepository.findAll()).thenReturn(addressList);
        Optional<List<Address>> all = iDemoService.getAll();
        Assertions.assertThat(addressList.size()).isEqualTo(all.get().size());
        Assertions.assertThat(addressList.get(0)).isEqualTo(all.get().get(0));



    }



}
