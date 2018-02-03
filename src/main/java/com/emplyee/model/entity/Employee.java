package com.emplyee.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    private Integer id;

    @NotBlank(message = "First name cannot be blank")
    @Length(min = 3, max = 20, message = "Length should be 3-20")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Length(min = 3, max = 20, message = "Length should be 3-20")
    private String lastName;

   /* @Min(value = 1, message = "Type shouldn't be less than 1")
    @Max(value = 7, message = "Type shouldn't be more than 7")*/
    private EmployeeType employeeType;

    private EmployeeStatus employeeStatus;

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeType=" + employeeType +
                ", employeeStatus=" + employeeStatus +
                '}';
    }
}