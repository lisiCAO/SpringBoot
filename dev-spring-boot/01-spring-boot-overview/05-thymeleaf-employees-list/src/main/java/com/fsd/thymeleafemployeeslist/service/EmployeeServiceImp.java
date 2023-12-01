package com.fsd.thymeleafemployeeslist.service;

import com.fsd.thymeleafemployeeslist.dao.EmployeeDAO;
import com.fsd.thymeleafemployeeslist.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImp(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeDAO.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDAO.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDAO.deleteById(id);
    }

}
