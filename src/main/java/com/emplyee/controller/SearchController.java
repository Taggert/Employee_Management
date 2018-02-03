package com.emplyee.controller;

import com.emplyee.model.entity.Employee;
import com.emplyee.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/search")
    public List<Employee> searchEmployee(@RequestBody Map<String, String> searchRequest) {

        return searchService.search(searchRequest);
    }


}