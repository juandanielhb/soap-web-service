package com.jdhb.soap.endpoint;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.domain.SaveEmployeeRequest;
import com.jdhb.soap.domain.SaveEmployeeResponse;
import com.jdhb.soap.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeEndpointTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeEndpoint employeeEndpoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveEmployee() {
        SaveEmployeeRequest request = new SaveEmployeeRequest();
        Employee employee = new Employee();
        request.setEmployee(employee);
        SaveEmployeeResponse expectedResponse = new SaveEmployeeResponse();
        expectedResponse.setEmployee(employee);

        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        SaveEmployeeResponse actualResponse = employeeEndpoint.saveEmployee(request);

        assertEquals(expectedResponse.getEmployee(), actualResponse.getEmployee());
    }
}
