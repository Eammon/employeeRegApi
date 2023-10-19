package com.montfi.employeeregistrationapi.repositories;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>
{

}
