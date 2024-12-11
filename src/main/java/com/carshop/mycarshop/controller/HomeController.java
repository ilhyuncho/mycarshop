package com.carshop.mycarshop.controller;

import com.carshop.mycarshop.config.UserDefineProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserDefineProperties userDefineProperties;

    @GetMapping("/healthcheck")
    public ResponseEntity<String> getGreeting() {


        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}

