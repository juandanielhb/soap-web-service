package com.jdhb.soap.service;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.entity.EmployeeEntity;
import com.jdhb.soap.exception.EmployeeDataAccessException;
import com.jdhb.soap.mapper.EmployeeMapper;
import com.jdhb.soap.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() throws EmployeeDataAccessException {
        Employee employee = new Employee();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        when(employeeMapper.toEmployeeEntity(employee)).thenReturn(employeeEntity);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(employeeMapper.toEmployee(employeeEntity)).thenReturn(employee);

        Employee savedEmployee = employeeService.save(employee);

        assertEquals(employee, savedEmployee);
    }

    @Test
    public void testSaveEmployeeWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.save(null);
        });
    }

    @Test
    public void testSaveEmployeeWithDatabaseError() {
        Employee employee = new Employee();

        when(employeeMapper.toEmployeeEntity(employee)).thenReturn(new EmployeeEntity());
        when(employeeRepository.save(any(EmployeeEntity.class))).thenThrow(new DataAccessException("Database error") {});

        assertThrows(EmployeeDataAccessException.class, () -> {
            employeeService.save(employee);
        });
    }
}