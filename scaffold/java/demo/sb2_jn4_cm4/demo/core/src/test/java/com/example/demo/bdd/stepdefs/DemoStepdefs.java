package com.example.demo.bdd.stepdefs;

import com.example.demo.model.Address;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class DemoStepdefs extends AbstractSteps implements En {
    public DemoStepdefs() {
        Given("^输入地址信息$", (DataTable t) -> {
            testContext().reset();
            List<Address> addressList = t.asList(Address.class);
            super.testContext().setPayload(addressList.get(0));

        });
        When("发送创建请求 {string}", (String testContext) -> {
            String createAddressUrl = "/v1/demo/add";
            executePost(createAddressUrl);
        });
        Then("保存成功 {string}", (String expectedResult) -> {
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "IS SUCCESSFUL":
                    assertThat(response.statusCode()).isIn(200, 201);
                    break;
                case "FAILS":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    break;
                default:
                    fail("Unexpected error");
            }

        });
    }

}
