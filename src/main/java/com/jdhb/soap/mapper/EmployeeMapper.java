package com.jdhb.soap.mapper;


import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.entity.EmployeeEntity;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeEntity employeeEntity);
    Iterable<Employee> toEmployees(Iterable<EmployeeEntity> employeeEntities);

    @InheritInverseConfiguration
    EmployeeEntity toEmployeeEntity(Employee employee);
}
