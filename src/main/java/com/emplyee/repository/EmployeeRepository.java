package com.emplyee.repository;

import com.emplyee.model.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee);
    List<Employee> getEmployees();


}