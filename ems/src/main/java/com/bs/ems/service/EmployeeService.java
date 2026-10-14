package com.bs.ems.service;

import com.bs.ems.dto.Employee;
import com.bs.ems.entities.EmployeeEntity;
import com.bs.ems.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        log.info("EmployeeService: add an employee");
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
        log.info("EmployeeService: add list of employees");
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
        log.info("EmployeeService: get all employees");
        return employeeRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    // Pagination + sorting
    @Transactional(readOnly = true)
    public Page<Employee> getAllEmployees(Pageable pageable) {
        log.info("EmployeeService: get all employees by pagination");
        return employeeRepository.findAll(pageable).map(this::mapToDTO);
    }

    public Employee getEmployeeById(Long id) {
        log.info("EmployeeService: get employee by id");
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        return mapToDTO(entity);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info("EmployeeService: update employee by id");
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setJobType(employeeDetails.getJobType());
        existingEmployee.setDesignation(employeeDetails.getDesignation());
        existingEmployee.setDepartment(employeeDetails.getDepartment());
        existingEmployee.setSalary(employeeDetails.getSalary());
        if (employeeDetails.getAddress() != null) {
            if (existingEmployee.getAddress() == null) {
                existingEmployee.setAddress(employeeDetails.getAddress());
            } else {
                existingEmployee.getAddress().setAddress(employeeDetails.getAddress().getAddress());
            }
        }
        EmployeeEntity updatedEntity = employeeRepository.save(existingEmployee);

        return mapToDTO(updatedEntity);
    }

    public void deleteEmployee(Long id) {
        log.info("EmployeeService: delete employee by id");
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(existingEmployee);
    }

    // Entity to -> DTO
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
