package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.service.IDemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

    private ObjectMapper om =new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IDemoService iDemoService;

    @Test
    public void test_address_add() throws Exception {

        Address address = new Address();
        address.setId(3L);
        address.setCity("杭州");
        address.setUserId(3L);
        address.setProvince("浙江");
        address.setStreet("颐和路");
        Optional<Address> addressOptional = Optional.of(address);
        Mockito.when(iDemoService.create(Mockito.any(Address.class))).thenReturn(addressOptional);


        String requestJson = om.writeValueAsString(address);

        MvcResult mvcResult = mockMvc.perform(post("/v1/demo/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("3"))
                .andReturn();
    }

    @Test
    public void test_address_list() throws Exception {
        Address address = new Address();
        address.setId(3L);
        address.setCity("杭州");
        address.setUserId(3L);
        address.setProvince("浙江");
        address.setStreet("颐和路");

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        Optional<List<Address>> mockAddressList = Optional.of(addressList);

        Mockito.when(iDemoService.getAll()).thenReturn(mockAddressList);


        mockMvc.perform(get("/v1/demo/list")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("3")).andReturn();

    }


}
