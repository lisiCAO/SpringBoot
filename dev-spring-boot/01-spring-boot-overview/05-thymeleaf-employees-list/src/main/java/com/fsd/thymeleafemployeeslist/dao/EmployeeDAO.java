package com.fsd.thymeleafemployeeslist.dao;

import com.fsd.thymeleafemployeeslist.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

}
