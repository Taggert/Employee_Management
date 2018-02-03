package com.emplyee.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EmployeeType {

    CREW_MEMBER(1),
    TEAM_LEADER(2),
    GROUP_LEADER(3),
    PROJECT_MANAGER(4),
    VP_RnD(5),
    VP(6),
    CEO(7);

    private Integer id;

    @JsonValue
    public Integer getId(){
        return id;
    }

    @JsonCreator
    public static EmployeeType getById(Integer id){
        for(EmployeeType employeeType : values()){
            if (employeeType.getId().equals(id)){
                return employeeType;
            }
        }
        return null;
    }


}