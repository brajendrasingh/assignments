package com.bs.ems.service;

import com.bs.ems.dto.Employee;
import com.bs.ems.entities.EmployeeEntity;
import com.bs.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        EmployeeEntity entity = EmployeeEntity.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .jobType(employee.getJobType())
                .designation(employee.getDesignation())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .address(employee.getAddress())
                .build();

        EmployeeEntity savedEntity = employeeRepository.save(entity);
        return mapToDTO(savedEntity);
    }

    public List<Employee> saveAllEmployee(List<Employee> employees) {
        List<EmployeeEntity> entityList = new ArrayList<>();
        for (Employee employee : employees) {
            entityList.add(EmployeeEntity.builder()
                    .name(employee.getName())
                    .email(employee.getEmail())
                    .jobType(employee.getJobType())
                    .designation(employee.getDesignation())
                    .department(employee.getDepartment())
                    .salary(employee.getSalary())
                    .address(employee.getAddress())
                    .build());
        }
        List<EmployeeEntity> savedEntity = employeeRepository.saveAll(entityList);
        return savedEntity.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public Employee getEmployeeById(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        return mapToDTO(entity);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setJobType(employeeDetails.getJobType());
        existingEmployee.setDesignation(employeeDetails.getDesignation());
        existingEmployee.setDepartment(employeeDetails.getDepartment());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setAddress(employeeDetails.getAddress());

        EmployeeEntity updatedEntity = employeeRepository.save(existingEmployee);

        return mapToDTO(updatedEntity);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(existingEmployee);
    }

    // Entity -> DTO
    private Employee mapToDTO(EmployeeEntity entity) {
        return Employee.builder().id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .jobType(entity.getJobType())
                .designation(entity.getDesignation())
                .department(entity.getDepartment())
                .salary(entity.getSalary())
                .address(entity.getAddress())
                .build();
    }
}
