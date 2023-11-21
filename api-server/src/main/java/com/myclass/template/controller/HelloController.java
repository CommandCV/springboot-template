package com.myclass.template.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/")
@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) throws UnknownHostException {
        log.info("Received request parameter: {}", name);
        InetAddress addr = InetAddress.getLocalHost();
        return "hello " + name + ", this is api server, ip: " + addr.getHostAddress() + ", port: "
                + port;
    }

}
