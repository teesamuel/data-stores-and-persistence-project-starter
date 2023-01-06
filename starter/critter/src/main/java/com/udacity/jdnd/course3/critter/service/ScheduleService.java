package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    public Schedule createSchedule(Schedule schedule, List<Long> petIds, List<Long> employeeIds)
    {
        List<Pet> pets = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        for (Long id : petIds)
        {
            Pet pet = petService.findPetById(id);
            pets.add(pet);
        }
        for (Long id : employeeIds)
        {
            Employee employee = employeeService.findEmployeeById(id);
            employees.add(employee);
        }
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        Pet pet = petService.findPetById(petId);
        return scheduleRepository.findSchedulesByPetsIn(Arrays.asList(pet));
    }

    public List<Schedule> getScheduleForEmployee(long employeeId)
    {
        Employee employee = employeeService.findEmployeeById(employeeId);
        return scheduleRepository.findSchedulesByEmployeesIn(Arrays.asList(employee));
    }

    public List<Schedule> getScheduleForCustomer(long customerId)
    {
        Customer customer = customerService.getCustomerById(customerId);
        return scheduleRepository.findSchedulesByPetsIn(customer.getPets());
    }
}

