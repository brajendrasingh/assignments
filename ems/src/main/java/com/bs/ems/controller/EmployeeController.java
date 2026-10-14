package com.bs.ems.controller;

import com.bs.ems.dto.Employee;
import com.bs.ems.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Endpoint to ADD a list of employees
    @PostMapping("/addAll")
    public ResponseEntity<List<Employee>> addAllEmployee(@RequestBody List<Employee> employee) {
        log.info("EmployeeController: add a list of employees");
        List<Employee> savedEmployee = employeeService.saveAllEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // 2. Endpoint to ADD a new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        log.info("EmployeeController: add an employee");
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // 3. Endpoint to RETURN ALL employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("EmployeeController: get all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // 4. Endpoint to RETURN ALL employees by pagination
    @GetMapping("/paged")
    public ResponseEntity<Page<Employee>> getEmployees(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "id") String sortBy,
                                                          @RequestParam(defaultValue = "asc") String direction) {
        log.info("EmployeeController: get all employees by pagination");
        if (page < 0 || size < 1 || size > 100) {
            throw new IllegalArgumentException("Page must be >= 0 and size must be between 1 and 100");
        }
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Employee> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

    // 5. RETURN details of an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("EmployeeController: get employee by Id");
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // 6. UPDATE an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        log.info("EmployeeController: update employee by Id");
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // 7. DELETE an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        log.info("EmployeeController: delete employee by Id");
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully.", HttpStatus.OK);
    }
}
