package com.emplyee.controller;

import com.emplyee.exceptions.InputValidationException;
import com.emplyee.model.entity.Employee;
import com.emplyee.model.web.EmployeeUpdateRequest;
import com.emplyee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping(value = "/save")
    public Employee saveEmployee(@RequestBody @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Has errors");
            System.out.println(result.getFieldErrors().toString());
            throw new InputValidationException(result);
        }
        return employeeService.save(employee);
    }

    @GetMapping(value = "/get")
    public List<Employee> getAllEmplyees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/update/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") Integer id, @RequestBody @Valid EmployeeUpdateRequest employeeUpdateRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return employeeService.updateEmployee(id, employeeUpdateRequest);
    }

    @GetMapping("/promote/{employeeId}")
    public Employee promoteEmployee(@PathVariable("employeeId") Integer id) {
        return employeeService.promoteEmployee(id);
    }

    @GetMapping("/demote/{employeeId}")
    public Employee demoteEmployee(@PathVariable("employeeId") Integer id) {
        return employeeService.demoteEmployee(id);
    }

    @GetMapping("/hiring/{employeeId}")
    public Employee hiringEmployee(@PathVariable("employeeId") Integer id) {
        return employeeService.hiringEmployee(id);
    }

    @GetMapping("/firing/{employeeId}")
    public Employee firingEmployee(@PathVariable("employeeId") Integer id) {
        return employeeService.firingEmployee(id);
    }

}