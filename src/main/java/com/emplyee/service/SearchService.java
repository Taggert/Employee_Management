package com.emplyee.service;

import com.emplyee.model.entity.Employee;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Employee> search(Map<String, String> map);
}