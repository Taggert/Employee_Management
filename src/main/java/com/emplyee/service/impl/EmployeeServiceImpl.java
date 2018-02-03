package com.emplyee.service.impl;

import com.emplyee.exceptions.EmployeeStatusMismatchException;
import com.emplyee.exceptions.GeneralAPIException;
import com.emplyee.model.entity.Employee;
import com.emplyee.model.entity.EmployeeStatus;
import com.emplyee.model.entity.EmployeeType;
import com.emplyee.model.web.EmployeeUpdateRequest;
import com.emplyee.repository.EmployeeRepository;
import com.emplyee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee save(Employee employee) {
        List<Employee> employees = employeeRepository.getEmployees();
        employee.setId(employees.size() + 1);
        employee.setEmployeeStatus(EmployeeStatus.CANDIDATE);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    @Override
    public Employee getEmployee(Integer id) {
        List<Employee> employees = employeeRepository.getEmployees();
        for (Employee e : employees) {
            if(e.getId() == id){
                return e;
            }
        }
        throw new GeneralAPIException("No such employee");
        //return employees.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Employee updateEmployee(Integer id, EmployeeUpdateRequest employeeUpdateRequest) {
        List<Employee> employees = employeeRepository.getEmployees();
        Employee employee = getEmployee(id);
        employee.setFirstName(employeeUpdateRequest.getFirstName());
        employee.setLastName(employeeUpdateRequest.getLastName());
        return employeeRepository.save(employee);

    }

    @Override
    public Employee promoteEmployee(Integer id) {
        Employee employee = getEmployee(id);
        if (!employee.getEmployeeStatus().equals(EmployeeStatus.HIRED)) {
           /* System.out.println("Employee should be hired");
            return null;*/
            throw new EmployeeStatusMismatchException("Employee should be hired");
        }
        if (employee.getEmployeeType().getId() == EmployeeType.values().length) {
            /*System.out.println("Employee isn't able to be promoted. He's on the hiest position.");
            return null;*/
            throw new GeneralAPIException("Employee isn't able to be promoted. He's on the hiest position.");
        }
        employee.setEmployeeType(EmployeeType.getById(employee.getEmployeeType().getId() + 1));
        return employeeRepository.save(employee);

    }

    @Override
    public Employee demoteEmployee(Integer id) {
        Employee employee = getEmployee(id);
        if (!employee.getEmployeeStatus().equals(EmployeeStatus.HIRED)) {
            /*System.out.println("Employee should be hired");
            return null;*/
            throw new EmployeeStatusMismatchException("Employee should be hired");
        }
        if (employee.getEmployeeType().getId() == 1) {
            /*System.out.println("Employee isn't able to be demoted. He's on the lowest position.");
            return null;*/
            throw new GeneralAPIException("Employee isn't able to be demoted. He's on the lowest position.");

        }
        employee.setEmployeeType(EmployeeType.getById(employee.getEmployeeType().getId() - 1));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee hiringEmployee(Integer id) {
        Employee employee = getEmployee(id);
        if (employee.getEmployeeStatus().equals(EmployeeStatus.HIRED)) {
            /*System.out.println("Employee is already hired");
            return employee;*/
            throw new EmployeeStatusMismatchException("Employee is already hired");
        }
        employee.setEmployeeStatus(EmployeeStatus.HIRED);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee firingEmployee(Integer id) {
        Employee employee = getEmployee(id);
        if (!employee.getEmployeeStatus().equals(EmployeeStatus.HIRED)) {
            /*System.out.println("Employee isn't hired, to be fired");
            return employee;*/
            throw new EmployeeStatusMismatchException("Employee isn't hired, to be fired");
        }
        employee.setEmployeeStatus(EmployeeStatus.FIRED);
        return employeeRepository.save(employee);
    }
}