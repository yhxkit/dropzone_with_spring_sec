package com.demo.ex18103101.controller.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityForResponse {

    public <T> ResponseEntity<?> get(T object){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new org.springframework.http.ResponseEntity<>(object, headers, HttpStatus.CREATED);
    }
}
