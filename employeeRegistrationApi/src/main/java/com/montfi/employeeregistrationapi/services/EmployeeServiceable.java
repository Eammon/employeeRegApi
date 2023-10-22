package com.montfi.employeeregistrationapi.services;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeServiceable {
    //defined an interface to abtract crud operations for the employee entity
    //we will implement this interface in the EmployeeService class
    //And we will use the methods in the EmployeeController class

    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee);
    void deleteEmployeeByObj(@RequestBody EmployeeEntity employee);
    EmployeeEntity deleteEmployeeById(@PathVariable long id);
    EmployeeEntity updateEmployee(@PathVariable long id,
                                                  @RequestBody EmployeeEntity oldEmployee);



}
