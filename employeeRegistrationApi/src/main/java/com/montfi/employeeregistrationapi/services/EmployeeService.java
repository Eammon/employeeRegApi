package com.montfi.employeeregistrationapi.services;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import com.montfi.employeeregistrationapi.exceptions.EmployeeDatabaseException;
import com.montfi.employeeregistrationapi.exceptions.ResourceNotFoundException;
import com.montfi.employeeregistrationapi.exceptions.ResourceNotSavedException;
import com.montfi.employeeregistrationapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceable
{

    //Inject the repository into the service class.
    //This will allow us to use the methods that we have created in the repository or extended.
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees() {

        return employeeRepository.findAll();

    }
    public EmployeeEntity createEmployee(EmployeeEntity employee) {

        //Create a new employee object to store the persisted employee
        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        //Confirm the employee was saved. If not, throw an exception
        if (savedEmployee == null) {
            throw new ResourceNotSavedException("Employee not saved");
        }

        //Now we can return the employee, so we can process it in the controller with a status code.
        return savedEmployee;

    }
    public void deleteEmployeeByObj(EmployeeEntity employee) {

        boolean employeeFound = employeeSearch(employee);

        if (employeeFound == false) {
            throw new ResourceNotFoundException("Employee not found, unable to delete");
        }
        else {
            //Employee found, delete it. What happens if we cant delete it?
            //try and catch the exception and throw a new one
            try {
                employeeRepository.delete(employee);
            } catch (Exception ex) {
                throw ex;//re throw the exception to the controller to handle
            }
        }
    }
    private boolean employeeSearch(EmployeeEntity employee) {

        boolean employeeFound = true;
        Optional<EmployeeEntity> existingEntity = employeeRepository
                .findById((int) employee.getId());

        if (existingEntity.isEmpty()) {
            employeeFound =  false;
        }
        return employeeFound;
    }
    public EmployeeEntity deleteEmployeeById(long id) {

        //First we need to find the employee by id, so we can delete it. We can use the find by id method from the repository
        EmployeeEntity employee = employeeRepository
                .findById(((int) id))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        try {
            employeeRepository.delete(employee);
        } catch (Exception ex) {
            throw new EmployeeDatabaseException("Issue when deleting employee", ex);
        }

        return employee;

    }
    public EmployeeEntity updateEmployee(long id, EmployeeEntity employeeDetails) {

          //First we need to find the employee that we want to update
          //use the find by id method from the repostory  to find the employee by id
          //Also use some exception handling by throwing our custom Exception in case the employee is not found
          //If the employee is found, we can update the employee details
            EmployeeEntity updatedEmployeeEntity = employeeRepository
                    .findById(((int) id))
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee not found. Unable to update employee details"));

            //Now we can update the employee details
            updatedEmployeeEntity.setFirstName(employeeDetails.getFirstName());
            updatedEmployeeEntity.setLastName(employeeDetails.getLastName());
            updatedEmployeeEntity.setEmail(employeeDetails.getEmail());

            //Confirm the employee was saved. If not, throw an exception
            if (employeeRepository.save(updatedEmployeeEntity) == null) throw new ResourceNotSavedException("Employee details not updated.");

        return updatedEmployeeEntity;
    }

}
