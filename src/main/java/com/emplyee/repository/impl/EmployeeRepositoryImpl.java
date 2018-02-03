package com.emplyee.repository.impl;

import com.emplyee.exceptions.GeneralAPIException;
import com.emplyee.model.entity.Employee;
import com.emplyee.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private ObjectMapper mapper = new ObjectMapper();

    @Value("${filePath}")
    String filePath = "";

    @Override
    @SneakyThrows
    public Employee save(Employee employee) {
        List<Employee> employees = getEmployees();
        boolean updateFlag =true;
        if (employee == null) {
            throw new GeneralAPIException("Nothing to save");
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == (employee.getId())) {
                updateFlag = false;
                employees.set(i, employee);
            }
        }
        if(updateFlag){
            employees.add(employee);
        }
        mapper.writeValue(new File(filePath), employees);
        return employee;
    }

    @SneakyThrows
    public List<Employee> getEmployees() {
        File repository = new File(filePath);
        if (!repository.exists()) {
            return new LinkedList<>();
        }
        return mapper.readValue(repository, new TypeReference<List<Employee>>() {
        });
    }
}