package com.montfi.employeeregistrationapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    //Create my firts endpoint to test connection to the API
    @GetMapping("/")
    public String index() {
        return "Welcome to Employee Registration API";
    }


}
