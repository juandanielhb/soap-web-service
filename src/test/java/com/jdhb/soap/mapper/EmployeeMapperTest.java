package com.jdhb.soap.mapper;

import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void mapEmployeeToEntity() throws DatatypeConfigurationException {
        LocalDate localDate = LocalDate.now();
        Employee employee = new Employee();
        employee.setId(BigInteger.valueOf(1L));
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDocumentType("ID");
        employee.setDocumentNumber("123456");
        employee.setBirthdate(localDateToXmlGregorianCalendar(localDate));
        employee.setJoiningDate(localDateToXmlGregorianCalendar(localDate));
        employee.setPosition("Developer");
        employee.setSalary(50000.0);

        EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employee);

        assertNotNull(employeeEntity);
        assertEquals(1, employeeEntity.getId());
        assertEquals("John", employeeEntity.getName());
        assertEquals("Doe", employeeEntity.getSurname());
        assertEquals("ID", employeeEntity.getDocumentType());
        assertEquals("123456", employeeEntity.getDocumentNumber());
        assertEquals(localDate, employeeEntity.getJoiningDate());
        assertEquals(localDate, employeeEntity.getBirthdate());
        assertEquals("Developer", employeeEntity.getPosition());
        assertEquals(50000.0, employeeEntity.getSalary());
    }

    @Test
    void mapEntityToEmployee() throws DatatypeConfigurationException {
        LocalDate localDate = LocalDate.now();
        XMLGregorianCalendar xmlGregorianCalendar = localDateToXmlGregorianCalendar(localDate);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("John");
        employeeEntity.setSurname("Doe");
        employeeEntity.setDocumentType("ID");
        employeeEntity.setDocumentNumber("123456");
        employeeEntity.setBirthdate(localDate);
        employeeEntity.setJoiningDate(localDate);
        employeeEntity.setPosition("Developer");
        employeeEntity.setSalary(50000.0);

        Employee employee = employeeMapper.toEmployee(employeeEntity);

        assertNotNull(employee);
        assertEquals(BigInteger.valueOf(1L), employee.getId());
        assertEquals("John", employee.getName());
        assertEquals("Doe", employee.getSurname());
        assertEquals("ID", employee.getDocumentType());
        assertEquals("123456", employee.getDocumentNumber());
        assertEquals(xmlGregorianCalendar.normalize(), employee.getJoiningDate());
        assertEquals(xmlGregorianCalendar, employee.getBirthdate());
        assertEquals("Developer", employee.getPosition());
        assertEquals(50000.0, employee.getSalary());
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate )
            throws DatatypeConfigurationException {
        if ( localDate == null ) {
            return null;
        }

        return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth(),
                DatatypeConstants.FIELD_UNDEFINED );
    }
}