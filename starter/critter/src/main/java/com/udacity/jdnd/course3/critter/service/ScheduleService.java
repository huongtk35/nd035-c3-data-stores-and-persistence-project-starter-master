/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:22
 * @modify date 2024-06-12 21:42:22
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {
@Autowired
    private ScheduleRepository scheduleRepository;


    public Schedule save(Schedule schedule) {
        return this.scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByEmployee(Employee employee) {
       return this.scheduleRepository.findScheduleByEmployees(employee);
    }

    public List<Schedule> findScheduleByPet(Pet pet) {
        return this.scheduleRepository.findScheduleByPets(pet);
    }
}
