package com.example.demo.controller;

import com.example.demo.config.BaseResult;
import com.example.demo.model.Address;
import com.example.demo.service.IDemoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Api(value = "swagger测试", protocols = "http")
@RestController
@RequestMapping(value = "/v1/demo")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private IDemoService iDemoService;

    @ApiOperation(value = "获取地址列表", notes = "查询地址列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 100, message = "异常数据")
    })
    public BaseResult<List<Address>> list() {
        List<Address> addresses = iDemoService.getAll().get();
        log.info("hello , test logs");
        return BaseResult.successWithData(addresses);
    }


    @ApiOperation(value = "创建地址", notes = "根据Address对象创建地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地址ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "city", value = "城市", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "province", value = "省", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "street", value = "街道", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "Long", paramType = "query")
    })
    @PostMapping(value = "add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResult<Address> add(@RequestBody  Address address) {
        Optional<Address> optionalAddress = iDemoService.create(address);
        return BaseResult.successWithData(optionalAddress.get());
    }

}
