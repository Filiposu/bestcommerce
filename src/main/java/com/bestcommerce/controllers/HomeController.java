package com.bestcommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getCategory(){
        return "hello";
    }
}
