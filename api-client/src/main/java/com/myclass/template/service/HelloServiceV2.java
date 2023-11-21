package com.myclass.template.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-server", url = "http://api-server:8090", path = "/")
public interface HelloServiceV2 {

    @GetMapping(value = "/hello")
    String hello(@RequestParam String name);

}
