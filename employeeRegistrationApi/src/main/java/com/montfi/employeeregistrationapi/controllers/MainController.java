package com.montfi.employeeregistrationapi.controllers;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import com.montfi.employeeregistrationapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/demo/")//This is the base url of the controller for all endpoints
public class MainController {

    //Create my first endpoint to test connection to the API
    @GetMapping("/")
    public String index() {
        return "Welcome to Employee Registration API";
    }

    //No I have injected the employee repository into my controller.
    //This will allow me to use the methods that I have created in the repository or extended.
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
