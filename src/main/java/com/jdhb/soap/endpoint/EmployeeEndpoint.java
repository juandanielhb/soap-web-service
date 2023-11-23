package com.jdhb.soap.endpoint;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.domain.SaveEmployeeRequest;
import com.jdhb.soap.domain.SaveEmployeeResponse;
import com.jdhb.soap.entity.EmployeeEntity;
import com.jdhb.soap.mapper.EmployeeMapper;
import com.jdhb.soap.mapper.EmployeeMapperImpl;
import com.jdhb.soap.repository.EmployeeRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://jdhb.com/soap/domain";
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeEndpoint(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse getEmployee(@RequestPayload SaveEmployeeRequest request) {
        EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(request.getEmployee());
        Employee employee = employeeMapper.toEmployee(employeeRepository.save(employeeEntity));
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setEmployee(employee);
        return response;
    }

}
