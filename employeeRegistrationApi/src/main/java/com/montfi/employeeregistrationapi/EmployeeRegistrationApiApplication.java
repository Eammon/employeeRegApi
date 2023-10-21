package com.montfi.employeeregistrationapi;

import com.montfi.employeeregistrationapi.entities.EmployeeEntity;
import com.montfi.employeeregistrationapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeRegistrationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRegistrationApiApplication.class, args);
	}

//	@Autowired
//	private EmployeeRepository employeeRepository;
//	@Override
//	public void run(String... args) throws Exception {
//		//We can add some users to the database here
//		//Add UserEntity objects to the database
//		EmployeeEntity employee1 = new EmployeeEntity();
//		employee1.setFirstName("John");
//		employee1.setLastName("Joe");
//		employee1.setEmail("joe.j@live.com");
//
//		//Save the user to the database
//		employeeRepository.save(employee1);
//
//		EmployeeEntity employee2 = new EmployeeEntity();
//		employee2.setFirstName("Mary");
//		employee2.setLastName("Jane");
//		employee2.setEmail("mary.jane@live.com");
}

