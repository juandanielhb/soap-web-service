package com.jdhb.soapwebservice;

import com.jdhb.soap_web_service.SaveEmployeeRequest;
import com.jdhb.soap_web_service.SaveEmployeeResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://jdhb.com/soap-web-service";
    private final EmployeeRepository employeeRepository;

    public EmployeeEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse getEmployee(@RequestPayload SaveEmployeeRequest request) {
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setEmployee(employeeRepository.findEmployee(request.getEmployee().getName()));

        return response;
    }

}
