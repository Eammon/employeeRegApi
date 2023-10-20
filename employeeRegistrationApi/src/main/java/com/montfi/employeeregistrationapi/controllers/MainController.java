package com.montfi.employeeregistrationapi.controllers;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import com.montfi.employeeregistrationapi.exceptions.ResourceNotFoundException;
import com.montfi.employeeregistrationapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
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

    //Lets add a delete employee endpoint
    //This endpoint will be mapped to the /delete_employee url
    //This endpoint will receive a DELETE request
    //This endpoint will receive a JSON object with the employee details
    //This endpoint will return a String
    //This endpoint will delete the employee from the database

    @PostMapping("/delete_employee_by_object")
    public String deleteEmployee(@RequestBody EmployeeEntity employee){
        employeeRepository.delete(employee);
        return "Employee deleted successfully";
    }


    //We have created a new endpoint to get an employee by id.
    //By using the @PathVariable annotation, we can get the id from the url
    //We can use the findById method from the repository to find the employee by id.
    //And using the ResponseEntity class, we can return the employee with a status code.
    //We can also use some exception handling by throwing our custom Exception in case the employee is not found
    @GetMapping("{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable long id){
        EmployeeEntity employee = employeeRepository
                .findById(((int) id))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        //Now we can return the employee with a status code
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployeeById(@PathVariable long id){
        EmployeeEntity employee = employeeRepository
                .findById(((int) id))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("update_employee/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable long id,
                                                         @RequestBody EmployeeEntity oldEmployee) {

        //First we need to find the employee that we want to update
        //use the find by id method from the repostory  to find the employee by id
        //Also use some exception handling by throwing our custom Exception in case the employee is not found
        //If the employee is found, we can update the employee details
        EmployeeEntity updatedEmployeeEntity = employeeRepository
                .findById(((int) id))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        //Now we can update the employee details
        updatedEmployeeEntity.setFirstName(
                oldEmployee.getFirstName());
        updatedEmployeeEntity.setLastName(
                oldEmployee.getLastName());
        updatedEmployeeEntity.setEmail(
                oldEmployee.getEmail());

        //Now we can save the updated employee to the database
        EmployeeEntity updatedEmployee = employeeRepository.save(updatedEmployeeEntity);

        //Now we can return the updated employee
        return ResponseEntity.ok(updatedEmployee);
    }


}
