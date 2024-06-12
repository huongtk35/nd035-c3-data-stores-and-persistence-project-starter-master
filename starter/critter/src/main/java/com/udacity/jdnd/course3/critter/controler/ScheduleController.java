/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:40:53
 * @modify date 2024-06-12 21:40:53
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Owner;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.OwnerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    @Transactional
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDate(scheduleDTO.getDate());
        if (!scheduleDTO.getPetIds().isEmpty()) {
            schedule.setPets(new ArrayList<>());
            for (Long petId : scheduleDTO.getPetIds()) {
                Pet pet = petService.getPetByPetId(petId);
                schedule.getPets().add(pet);
            }
        }
        schedule.setActivities(scheduleDTO.getActivities());
        if (!scheduleDTO.getEmployeeIds().isEmpty()) {
            schedule.setEmployees(new ArrayList<>());
            for (Long employeeId : scheduleDTO.getEmployeeIds()) {
                Employee employee = employeeService.getEmployeeById(employeeId);
                schedule.getEmployees().add(employee);
            }
        }
        Schedule newSchedule = scheduleService.save(schedule);
        scheduleDTO.setId(newSchedule.getId());
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getAllSchedules();
        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setId(schedule.getId());
            scheduleDTO.setDate(schedule.getDate());
            scheduleDTO.setActivities(schedule.getActivities());
            if (!schedule.getPets().isEmpty()) {
                scheduleDTO.setPetIds(new ArrayList<>());
                for (Pet pet : schedule.getPets()) {
                    scheduleDTO.getPetIds().add(pet.getId());
                }
            }

            if (!schedule.getEmployees().isEmpty()) {
                scheduleDTO.setEmployeeIds(new ArrayList<>());
                for (Employee employee : schedule.getEmployees()) {
                    scheduleDTO.getEmployeeIds().add(employee.getId());
                }
            }
            scheduleDTOS.add(scheduleDTO);

        }
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        Pet pet = petService.getPetByPetId(petId);

        List<Schedule> schedules = scheduleService.findScheduleByPet(pet);
        if (!schedules.isEmpty()) {
            for (Schedule schedule : schedules) {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setId(schedule.getId());
                scheduleDTO.setDate(schedule.getDate());
                scheduleDTO.setActivities(schedule.getActivities());
                if (!schedule.getPets().isEmpty()) {
                    scheduleDTO.setPetIds(new ArrayList<>());
                    for (Pet item : schedule.getPets()) {
                        scheduleDTO.getPetIds().add(item.getId());
                    }
                }

                if (!schedule.getEmployees().isEmpty()) {
                    scheduleDTO.setEmployeeIds(new ArrayList<>());
                    for (Employee em : schedule.getEmployees()) {
                        scheduleDTO.getEmployeeIds().add(em.getId());
                    }
                }
                scheduleDTOS.add(scheduleDTO);
            }
        }

        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        Employee employee = employeeService.getEmployeeById(employeeId);

        List<Schedule> schedules = scheduleService.findScheduleByEmployee(employee);
        if (!schedules.isEmpty()) {
            for (Schedule schedule : schedules) {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setId(schedule.getId());
                scheduleDTO.setDate(schedule.getDate());
                scheduleDTO.setActivities(schedule.getActivities());
                if (!schedule.getPets().isEmpty()) {
                    scheduleDTO.setPetIds(new ArrayList<>());
                    for (Pet item : schedule.getPets()) {
                        scheduleDTO.getPetIds().add(item.getId());
                    }
                }

                if (!schedule.getEmployees().isEmpty()) {
                    scheduleDTO.setEmployeeIds(new ArrayList<>());
                    for (Employee em : schedule.getEmployees()) {
                        scheduleDTO.getEmployeeIds().add(em.getId());
                    }
                }
                scheduleDTOS.add(scheduleDTO);
            }
        }

        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        Owner owner = ownerService.getOwnerById(customerId);
        if (!owner.getPets().isEmpty()) {
            for (Pet pet : owner.getPets()) {
                List<Schedule> schedules = scheduleService.findScheduleByPet(pet);
                if (!schedules.isEmpty()) {
                    for (Schedule schedule : schedules) {
                        ScheduleDTO scheduleDTO = new ScheduleDTO();
                        scheduleDTO.setId(schedule.getId());
                        scheduleDTO.setDate(schedule.getDate());
                        scheduleDTO.setActivities(schedule.getActivities());
                        if (!schedule.getPets().isEmpty()) {
                            scheduleDTO.setPetIds(new ArrayList<>());
                            for (Pet item : schedule.getPets()) {
                                scheduleDTO.getPetIds().add(item.getId());
                            }
                        }

                        if (!schedule.getEmployees().isEmpty()) {
                            scheduleDTO.setEmployeeIds(new ArrayList<>());
                            for (Employee em : schedule.getEmployees()) {
                                scheduleDTO.getEmployeeIds().add(em.getId());
                            }
                        }
                        scheduleDTOS.add(scheduleDTO);
                    }
                }
            }
        }
        return scheduleDTOS;
    }
}
