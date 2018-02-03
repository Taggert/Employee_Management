package com.emplyee.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EmployeeStatus {

    CANDIDATE(1),
    HIRED(2),
    FIRED(3);

    private Integer id;

    @JsonValue
    public Integer getId() {
        return id;
    }

    @JsonCreator
    public static EmployeeStatus getById(Integer id){
        for(EmployeeStatus employeeStatus : values()){
            if (employeeStatus.getId().equals(id)){
                return employeeStatus;
            }
        }
        return null;
    }
}