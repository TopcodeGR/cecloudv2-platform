package com.ptopalidis.cecloud.platform.user.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/demo")
    public Authentication demo(Authentication a){
        return a;
    }
}
