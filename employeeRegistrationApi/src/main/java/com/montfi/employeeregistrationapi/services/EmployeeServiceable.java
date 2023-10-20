package com.montfi.employeeregistrationapi.services;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeServiceable {
    //defined an interface to abtract crud operations for the employee entity
    //we will implement this interface in the EmployeeService class
    //And we will use the methods in the EmployeeController class

    List<EmployeeEntity> getAllEmployees();
    ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee);
    ResponseEntity<EmployeeEntity> deleteEmployee(@RequestBody EmployeeEntity employee);
    ResponseEntity<EmployeeEntity> deleteEmployeeById(@PathVariable long id);
    ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable long id,
                                                  @RequestBody EmployeeEntity oldEmployee);



}
