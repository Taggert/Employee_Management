package com.emplyee.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeUpdateRequest {


    @org.hibernate.validator.constraints.NotBlank(message = "First name cannot be blank")
    @Length(min = 3, max = 20, message = "Length should be 3-20")
    private String firstName;

    @org.hibernate.validator.constraints.NotBlank(message = "Last name cannot be blank")
    @Length(min = 3, max = 20, message = "Length should be 3-20")
    private String lastName;


}