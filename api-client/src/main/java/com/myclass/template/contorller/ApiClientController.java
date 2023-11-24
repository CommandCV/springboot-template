package com.myclass.template.contorller;

import com.myclass.template.service.HelloService;
import com.myclass.template.service.HelloServiceV2;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class ApiClientController {

    private final HelloService helloService;

    private final HelloServiceV2 helloServiceV2;

    private final Counter apiCounter;

    @Autowired
    public ApiClientController(HelloService helloService, HelloServiceV2 helloServiceV2,
            MeterRegistry meterRegistry) {
        this.helloService = helloService;
        this.helloServiceV2 = helloServiceV2;
        this.apiCounter = Counter.builder("api_counter")
                .description("api counter")
                .register(meterRegistry);
    }

    @Timed(value = "hello_v1", percentiles = {0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.98, 0.99, 0.999})
    @GetMapping(value = "/hello/v1")
    public String hello() {
        apiCounter.increment();
        return helloService.hello("api client");
    }

    @Timed(value = "hello_v2", percentiles = {0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.98, 0.99, 0.999})
    @GetMapping(value = "/hello/v2")
    public String helloV2() {
        apiCounter.increment(1);
        return helloServiceV2.hello("api client v2");
    }

}
