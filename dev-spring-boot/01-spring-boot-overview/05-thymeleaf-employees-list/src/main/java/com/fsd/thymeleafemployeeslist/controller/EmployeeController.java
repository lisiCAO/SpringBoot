package com.fsd.thymeleafemployeeslist.controller;

import com.fsd.thymeleafemployeeslist.entity.Employee;
import com.fsd.thymeleafemployeeslist.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService  employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<Employee> theEmployees = employeeService.getAllEmployees();

        theModel.addAttribute("employees", theEmployees);
        return "list-employees";
    }


}
