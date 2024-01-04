package com.khoabeo.demojwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("KHOA BEO NUMBER ONE");
    }

    @GetMapping("/greet")
    public ResponseEntity<String> hello123() {
        return ResponseEntity.ok("KHOA BEO NUMBER ONE");
    }
}