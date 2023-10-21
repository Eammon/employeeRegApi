package com.montfi.employeeregistrationapi.controllers;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import com.montfi.employeeregistrationapi.exceptions.EmployeeDatabaseException;
import com.montfi.employeeregistrationapi.exceptions.ResourceNotFoundException;
import com.montfi.employeeregistrationapi.exceptions.ResourceNotSavedException;
import com.montfi.employeeregistrationapi.repositories.EmployeeRepository;
import com.montfi.employeeregistrationapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/show_employees")
    public List<EmployeeEntity> getEmployees() {

        return employeeService.getAllEmployees();
    }


    //Lets add a create employee endpoint
    //This endpoint will be mapped to the /create_employee url
    //This endpoint will receive a POST request
    //This endpoint will receive a JSON object with the employee details
    //This endpoint will return a String
    //This endpoint will save the employee to the database

    //Note: to open any declared endpoint, we need to run the application and go to http://localhost:8080/api/demo/employees
    @PostMapping("/create_employee")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody
                                                          EmployeeEntity employee) {
        //Confirm parameters are not null. if they are It's an exceptional circumstance
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        //Now we can return the employee, so we can process it in the controller with a ok status code.
       return ResponseEntity.ok(employeeService.createEmployee(employee));
    }
    /*
        Lets add a delete employee endpoint
        This endpoint will be mapped to the /delete_employee url
        This endpoint will receive a DELETE request
        This endpoint will receive a JSON object with the employee details
        This endpoint will return a String
        This endpoint will delete the employee from the database
    */
    @PostMapping("/delete_employee_by_object")
    public ResponseEntity<EmployeeEntity> deleteEmployee(@RequestBody EmployeeEntity employee)
    {
        //have status var to return.
        //if employee is found and deleted, return 200
        //if employee is not found, return 404
        //if employee is found but not deleted, return 500
        //if employee is null, return 400
        if (employee == null) {
            throw new ResourceNotFoundException("Employee cannot be null");
        }

        try {
            employeeService.deleteEmployeeByObj(employee);
            return ResponseEntity.ok(employee);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //We have created a new endpoint to get an employee by id.
    //By using the @PathVariable annotation, we can get the id from the url
    //We can use the findById method from the repository to find the employee by id.
    //And using the ResponseEntity class, we can return the employee with a status code.
    //We can also use some exception handling by throwing our custom Exception in case the employee is not found
    @GetMapping("/get_Employee{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable long id){

        EmployeeEntity employee = employeeRepository
                .findById(((int) id))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        //Now we can return the employee with a status code
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable long id){
        //Now I have abtracted the delete employee logic to the service class
        //I can call the delete employee method from the service class
        //And pass the id of the employee to delete
        try {
            employeeService.deleteEmployeeById(id);

            return ResponseEntity.ok().build();

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }
    @PutMapping("update_employee_details/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeeDetails(@PathVariable long id,
                                                         @RequestBody EmployeeEntity newEmployeeDetails) {
        try {
            employeeService.updateEmployee(id, newEmployeeDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();

    }
}
