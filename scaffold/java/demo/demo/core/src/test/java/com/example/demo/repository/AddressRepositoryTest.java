package com.example.demo.repository;

import com.example.demo.model.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    @Resource
    private AddressRepository addressRepository;



    @Test
    public void test_address_add(){
        Address address = new Address();
        address.setId(3L);
        address.setCity("杭州");
        address.setUserId(3L);
        address.setProvince("浙江");
        address.setStreet("颐和路");
        Address save = addressRepository.save(address);
        Assertions.assertThat(address.getCity()).isEqualTo(save.getCity());


    }


    @Test
    public void test_address_list(){
        Address address = new Address();
        address.setCity("杭州");
        address.setUserId(3L);
        address.setProvince("浙江");
        address.setStreet("颐和路");

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        entityManager.persistAndFlush(address);
        List<Address> all = addressRepository.findAll();
        Assertions.assertThat(addressList.size()).isEqualTo(all.size());
        Assertions.assertThat(addressList.get(0).getCity()).isEqualTo(all.get(0).getCity());



    }



}
