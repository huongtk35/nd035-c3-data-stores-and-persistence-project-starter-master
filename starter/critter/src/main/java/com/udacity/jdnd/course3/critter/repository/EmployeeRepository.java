/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:41:59
 * @modify date 2024-06-12 21:41:59
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Set<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek);
}