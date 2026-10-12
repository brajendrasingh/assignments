package com.bs.ems.dto;

import com.bs.ems.entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Employee {
    private Long id;
    private String name;
    private String email;
    private String jobType;
    private String designation;
    private String department;
    private double salary;
    private AddressEntity addressEntity;
}
