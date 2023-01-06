package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee saveEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId)
    {
        Employee employee = findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
    }

    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skillsNeeded, Set<DayOfWeek> dayOfWeeks) {
        List<Employee> employees = employeeRepository.findBySkillsInAndDaysAvailableIn(skillsNeeded, dayOfWeeks);
        List<Employee> resultEmployees = new ArrayList<>();
        for (Employee employee : employees)
        {
            if(!resultEmployees.contains(employee) && employee.getSkills().containsAll(skillsNeeded))
            {
                resultEmployees.add(employee);
            }
        }
        return resultEmployees;
    }
}
