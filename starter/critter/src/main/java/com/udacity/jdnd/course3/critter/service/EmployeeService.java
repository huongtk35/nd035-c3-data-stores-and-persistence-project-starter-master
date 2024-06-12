/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:14
 * @modify date 2024-06-12 21:42:14
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee getEmployeeById(long employeeId) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        return optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + employeeId + " not found"));
    }

    public Set<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek) {
        return this.employeeRepository.findEmployeeByDaysAvailable(dayOfWeek);
    }
}
