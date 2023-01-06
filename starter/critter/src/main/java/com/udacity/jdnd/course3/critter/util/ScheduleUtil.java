package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleUtil {
    public static Schedule transformScheduleDTOtoEntity(ScheduleDTO scheduleDTO)
    {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    public static ScheduleDTO transformEntityToScheduleDTO(Schedule schedule)
    {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee : schedule.getEmployees())
        {
            employeeIds.add(employee.getId());
        }
        for (Pet pet : schedule.getPets())
        {
            petIds.add(pet.getId());
        }
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }


    public static List<ScheduleDTO> transformEntityListToScheduleDTOList(List<Schedule> scheduleList)
    {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule schedule : scheduleList)
        {
            ScheduleDTO scheduleDTO = transformEntityToScheduleDTO(schedule);
            scheduleDTOList.add(scheduleDTO);
        }
        return scheduleDTOList;
    }
}
