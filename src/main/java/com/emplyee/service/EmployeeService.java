package com.emplyee.service;

import com.emplyee.model.entity.Employee;
import com.emplyee.model.web.EmployeeUpdateRequest;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployee(Integer id);
    Employee updateEmployee(Integer id, EmployeeUpdateRequest employeeUpdateRequest);
    Employee promoteEmployee(Integer id);
    Employee demoteEmployee(Integer id);
    Employee hiringEmployee(Integer id);
    Employee firingEmployee(Integer id);
}