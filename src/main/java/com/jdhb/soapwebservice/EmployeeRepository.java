package com.jdhb.soapwebservice;

import com.jdhb.soap_web_service.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeRepository {

    private static final Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {
        Employee employee1 = new Employee();
        employee1.setName("Daniel");
        employee1.setSurname("Hernandez");
        employee1.setDocumentNumber("1102374961");
        employee1.setDocumentType("CC");

        GregorianCalendar birthdate = new GregorianCalendar(1994, 8, 16);
        XMLGregorianCalendar XMLBirthdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(birthdate);
        employee1.setBirthdate(XMLBirthdate);
        GregorianCalendar joiningDate = new GregorianCalendar(1994, 8, 16);
        XMLGregorianCalendar XMLJoiningDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(joiningDate);
        employee1.setJoiningDate(XMLJoiningDate);
        employee1.setPosition("Java Developer");
        employee1.setSalary(7000000);

        employees.put(employee1.getName(), employee1);
    }

    public Employee saveEmployee(Employee employee){
        return employee;
    }
    public Employee findEmployee(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return employees.get(name);
    }
}
