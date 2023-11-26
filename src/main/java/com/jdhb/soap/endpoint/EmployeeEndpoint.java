package com.jdhb.soap.endpoint;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.domain.SaveEmployeeRequest;
import com.jdhb.soap.domain.SaveEmployeeResponse;
import com.jdhb.soap.mapper.EmployeeMapper;
import com.jdhb.soap.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://jdhb.com/soap/domain";
    private final EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
        Employee employee = request.getEmployee();
        log.info("Save employee identified by {} {}", employee.getDocumentType(), employee.getDocumentNumber());
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        Employee employeeSaved = employeeService.save(employee);
        response.setEmployee(employeeSaved);
        return response;
    }

}
