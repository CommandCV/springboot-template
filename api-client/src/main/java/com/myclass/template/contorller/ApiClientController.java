package com.myclass.template.contorller;

import com.myclass.template.service.HelloService;
import com.myclass.template.service.HelloServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class ApiClientController {

    private final HelloService helloService;

    private final HelloServiceV2 helloServiceV2;

    @Autowired
    public ApiClientController(HelloService helloService, HelloServiceV2 helloServiceV2) {
        this.helloService = helloService;
        this.helloServiceV2 = helloServiceV2;
    }

    @GetMapping(value = "/hello/v1")
    public String hello() {
        return helloService.hello("api client");
    }

    @GetMapping(value = "/hello/v2")
    public String helloV2() {
        return helloServiceV2.hello("api client v2");
    }

}
