package com.montfi.employeeregistrationapi.services;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class EmployeeService implements EmployeeServiceable
{
    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeEntity> createEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeEntity> deleteEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeEntity> deleteEmployeeById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeEntity> updateEmployee(long id, EmployeeEntity oldEmployee) {
        return null;
    }
}
