package com.fsd.thymeleafemployeeslist.service;


import com.fsd.thymeleafemployeeslist.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    void saveEmployee(Employee employee);
    void deleteEmployee(Long id);
}
