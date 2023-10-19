package com.montfi.employeeregistrationapi.controllers;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import com.montfi.employeeregistrationapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo/")//This is the base url of the controller for all endpoints
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
    @GetMapping("/show_employees")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Lets add a create employee endpoint
    //This endpoint will be mapped to the /create_employee url
    //This endpoint will receive a POST request
    //This endpoint will receive a JSON object with the employee details
    //This endpoint will return a String
    //This endpoint will save the employee to the database

    //Note: to open any declared endpoint, we need to run the application and go to http://localhost:8080/api/demo/employees
    @PostMapping("/create_employee")
    public String createEmployee(@RequestBody EmployeeEntity employee) {
        employeeRepository.save(employee);
        return "Employee created successfully";
    }


}
