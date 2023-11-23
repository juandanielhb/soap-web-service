package com.jdhb.soap.mapper;


import com.jdhb.soap.domain.Employee;
import com.jdhb.soap.entity.EmployeeEntity;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "surname", target = "surname"),
//            @Mapping(source = "documentType", target = "documentType"),
//            @Mapping(source = "documentNumber", target = "documentNumber"),
//            @Mapping(source = "position", target = "position"),
//            @Mapping(source = "salary", target = "salary"),
//            @Mapping(source = "birthdate", target = "birthdate"),
//            @Mapping(source = "joiningDate", target = "joiningDate"),
//    })
//    @Mapping(source = "id", target = "id", ignore = true)
    Employee toEmployee(EmployeeEntity employeeEntity);
    Iterable<Employee> toEmployees(Iterable<EmployeeEntity> employeeEntities);

    @InheritInverseConfiguration
    EmployeeEntity toEmployeeEntity(Employee employee);
}
