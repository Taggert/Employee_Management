package com.emplyee.service.impl;

import com.emplyee.model.entity.Employee;
import com.emplyee.repository.EmployeeRepository;
import com.emplyee.service.SearchService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @SneakyThrows
    public List<Employee> search(Map<String, String> map) {
        List<Employee> employees = employeeRepository.getEmployees();
        List<Employee> res = new ArrayList<>();
        Set<String> fields = map.keySet();
        for (Employee e : employees) {
            here:
            for (Field field : e.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                for (String f: fields) {
                    if(field.getName().equals(f)){
                        if(field.get(e).equals(map.get(f))){
                            res.add(e);
                            continue here;
                        }
                    }
                }
            }
        }
        return res;
    }
}