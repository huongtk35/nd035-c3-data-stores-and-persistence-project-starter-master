/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:41:25
 * @modify date 2024-06-12 21:41:25
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.entity;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    @CollectionTable(name = "skills")
    @Enumerated(EnumType.STRING)
    @Column(name = "skills", nullable = false)
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @CollectionTable(name = "daysAvailable")
    @Enumerated(EnumType.STRING)
    @Column(name = "daysAvailable", nullable = false)
    private Set<DayOfWeek> daysAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}