/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:40:58
 * @modify date 2024-06-12 21:40:58
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.controler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Owner;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.OwnerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.UserService;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/customer")
    @Transactional
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Owner owner = new Owner();
        owner.setOwnerId(customerDTO.getId());
        owner.setName(customerDTO.getName());
        owner.setPhoneNumber(customerDTO.getPhoneNumber());
        owner.setNotes(customerDTO.getNotes());
        if (customerDTO.getPetIds() != null) {
            for (Long petId : customerDTO.getPetIds()) {
                Pet p = petService.getPetByPetId(petId);
                owner.getPets().add(p);
            }
        }

        Owner newOwner = ownerService.saveOwner(owner);
        customerDTO.setId(newOwner.getOwnerId());
        return customerDTO;
    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Owner> owners = ownerService.getAllCustomers();
        for (Owner owner : owners) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(owner.getOwnerId());
            customerDTO.setName(owner.getName());
            customerDTO.setNotes(owner.getNotes());
            customerDTO.setPhoneNumber(owner.getPhoneNumber());
            if (owner.getPets() != null) {
                for (Pet petTemp : owner.getPets()) {
                    if (customerDTO.getPetIds() == null) {
                        customerDTO.setPetIds(new ArrayList<>());
                    }
                    customerDTO.getPetIds().add(petTemp.getId());
                }
            }

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = petService.getPetByPetId(petId);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(pet.getOwner().getOwnerId());
        customerDTO.setName(pet.getOwner().getName());
        customerDTO.setNotes(pet.getOwner().getNotes());
        customerDTO.setPhoneNumber(pet.getOwner().getPhoneNumber());
        customerDTO.setPetIds(new ArrayList<>());
        for (Pet petTemp : pet.getOwner().getPets()) {
            customerDTO.getPetIds().add(petTemp.getId());
        }
        return customerDTO;
    }

    @PostMapping("/employee")
    @Transactional
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employee.getId() != null ? employee.getId() : -1);
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        Employee newEmployee = employeeService.save(employee);
        employeeDTO.setId(newEmployee.getId());
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setSkills(employee.getSkills());
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    @Transactional
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.save(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        LocalDate localDate = employeeDTO.getDate();
        HashSet<EmployeeSkill> skills = new HashSet<>(employeeDTO.getSkills());
        Set<Employee> availableEmployees = employeeService.findEmployeeByDaysAvailable(localDate.getDayOfWeek());
        if (!availableEmployees.isEmpty()) {
            for (Employee employee : availableEmployees
            ) {
                boolean matchedSkillSet = employee.getSkills().containsAll(skills);

                if (matchedSkillSet) {
                    EmployeeDTO employeeDTOTemp = new EmployeeDTO();
                    employeeDTOTemp.setId(employee.getId());
                    employeeDTOTemp.setName(employee.getName());
                    employeeDTOTemp.setDaysAvailable(employee.getDaysAvailable());
                    employeeDTOTemp.setSkills(employee.getSkills());
                    employeeDTOS.add(employeeDTOTemp);
                }

            }
        }
        return employeeDTOS;
    }

}
