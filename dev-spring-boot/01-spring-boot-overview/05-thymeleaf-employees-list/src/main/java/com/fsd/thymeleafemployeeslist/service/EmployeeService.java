package com.fsd.thymeleafemployeeslist.service;


import com.fsd.thymeleafemployeeslist.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    void deleteEmployee(Long id);
}
