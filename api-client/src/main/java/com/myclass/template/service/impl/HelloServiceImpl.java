package com.myclass.template.service.impl;

import com.myclass.template.service.HelloService;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    private static final RestTemplate restTemplate = new RestTemplate(
            new SimpleClientHttpRequestFactory());

    private static final String URL = "http://api-server:8090/hello";

    @Override
    public String hello(String name) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("name", name)
                .build();
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toUri(),
                HttpMethod.GET, new HttpEntity<>(new LinkedHashMap<>()), String.class);
        return response.getBody();
    }
}
