package com.jdhb.soap.service;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){




        return employee;
    }
}
