package com.jdhb.soap.service;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.entity.EmployeeEntity;
import com.jdhb.soap.exception.EmployeeDataAccessException;
import com.jdhb.soap.mapper.EmployeeMapper;
import com.jdhb.soap.repository.EmployeeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public Employee save(Employee employee) throws EmployeeDataAccessException {
        if (employee == null) {
            throw new IllegalArgumentException("Resource can not be null");
        }

        try {
            EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employee);
            EmployeeEntity employeeEntityResult = employeeRepository.save(employeeEntity);
            return employeeMapper.toEmployee(employeeEntityResult);
        } catch (DataAccessException e) {
            throw new EmployeeDataAccessException("Resource could not be saved", e);
        }
    }

}
