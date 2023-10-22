package com.montfi.employeeregistrationapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees_table")
public class EmployeeEntity {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    //Now let's create some simple details for our employee
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

}
