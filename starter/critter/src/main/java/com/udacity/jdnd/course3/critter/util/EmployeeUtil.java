package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.Employee;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil {
    public static EmployeeDTO transformEntityToEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public static Employee transformEmployeeDTOtoEntity(EmployeeDTO employeeDTO)
    {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public static List<EmployeeDTO> transformEnitityListToEmployeeDTOList(List<Employee> employees)
    {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employees)
        {
            employeeDTOList.add(transformEntityToEmployeeDTO(employee));
        }
        return employeeDTOList;
    }
}
