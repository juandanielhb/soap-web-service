package com.jdhb.soap.endpoint;

import com.jdhb.soap.domain.SaveEmployeeRequest;
import com.jdhb.soap.domain.SaveEmployeeResponse;
import com.jdhb.soap.entity.EmployeeEntity;
import com.jdhb.soap.repository.EmployeeRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;

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
        com.jdhb.soap.domain.Employee employeeSOAP = request.getEmployee();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeSOAP.getName());
        employeeEntity.setSurname(employeeSOAP.getSurname());
        employeeEntity.setDocumentType(employeeSOAP.getDocumentType());
        employeeEntity.setDocumentNumber(employeeSOAP.getDocumentNumber());
        employeeEntity.setPosition(employeeSOAP.getPosition());
        employeeEntity.setSalary(employeeSOAP.getSalary());

        LocalDate birthdate = LocalDate.of(
                employeeSOAP.getBirthdate().getYear(),
                employeeSOAP.getBirthdate().getMonth(),
                employeeSOAP.getBirthdate().getDay());
        employeeEntity.setBirthdate(birthdate);

        LocalDate joiningDate = LocalDate.of(
                employeeSOAP.getJoiningDate().getYear(),
                employeeSOAP.getJoiningDate().getMonth(),
                employeeSOAP.getJoiningDate().getDay());
        employeeEntity.setJoiningDate(joiningDate);


        employeeRepository.save(employeeEntity);

        response.setEmployee(employeeSOAP);

        return response;
    }

}
