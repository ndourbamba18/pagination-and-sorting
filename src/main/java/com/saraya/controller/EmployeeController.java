package com.saraya.controller;

import com.saraya.dto.EmployeeDto;

import com.saraya.entities.Employee;
import com.saraya.message.ResponseMessage;
import com.saraya.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "*")
public class EmployeeController {

   private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    /*
     * GETTING ALL TRAININGS
     * URL : http://127.0.0.1:8080/api/v1/employees
     * PAGINATION AND SORTING :
     * URL : http://127.0.0.1:8080/api/v1/employees?page=index
     * URL : http://127.0.0.1:8080/api/v1/employees?size=number
     * URL : http://127.0.0.1:8080/api/v1/employees?page=index&size=number
     */
    @GetMapping(path = "/employees")
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        try {
            List<Employee> employees = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<Employee> pageEmployees;
            if (name == null)
                pageEmployees = employeeRepository.findAll(paging);
            else
                pageEmployees = employeeRepository.findByNameContaining(name, paging);

            employees = pageEmployees.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("employees", employees);
            response.put("currentPage", pageEmployees.getNumber());
            response.put("totalItems", pageEmployees.getTotalElements());
            response.put("totalPages", pageEmployees.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GET THE LIST OF MALE EMPLOYEES
     * URL : http://127.0.0.1:8080/api/v1/employees/genger
     */
    @GetMapping(path = "/employees/gender")
    public ResponseEntity<Map<String, Object>> getEmployeeGender(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
            List<Employee> employees = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<Employee> pageEmployees;

            pageEmployees = employeeRepository.findByGender('M', paging);
            employees = pageEmployees.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("employees", employees);
            response.put("currentPage", pageEmployees.getNumber());
            response.put("totalItems", pageEmployees.getTotalElements());
            response.put("totalPages", pageEmployees.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GETTING A SINGLE PRODUCT BY ID
     * URL : http://127.0.0.1:8080/api/v1/employees/detail/{employeeId}
     */
    @GetMapping(path = "/employees/detail/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long employeeId){
        try {
            if (!employeeRepository.existsById(employeeId))
                return new ResponseEntity<>(new ResponseMessage("Employee does not exist : "+employeeId+"!"), HttpStatus.BAD_GATEWAY);
            Employee employee = employeeRepository.findById(employeeId).get();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GETTING A SINGLE PRODUCT BY EMAIL
     * URL : http://127.0.0.1:8080/api/v1/employees/detail-email/{employeeEmail}
     */
    @GetMapping(path = "/employees/detail-email/{employeeEmail}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable("employeeEmail") String employeeEmail){
        try {
            if (!employeeRepository.existsByEmail(employeeEmail))
                return new ResponseEntity<>(new ResponseMessage("Employee does not exist : "+employeeEmail), HttpStatus.BAD_REQUEST);
            Employee employee = employeeRepository.findByEmailContaining(employeeEmail).get();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GETTING A SINGLE PRODUCT BY PHONE NUMBER
     * URL : http://127.0.0.1:8080/api/v1/employees/detail-phone/{employeePhone}
     */
    @GetMapping(path = "/employees/detail-phone/{employeePhone}")
    public ResponseEntity<?> getEmployeeByPhoneNumber(@PathVariable("employeePhone") String employeePhone){
       try {
           if (!employeeRepository.existsByPhone(employeePhone))
               return new ResponseEntity<>(new ResponseMessage("Employee does not exist : "+employeePhone), HttpStatus.BAD_REQUEST);
           Employee employee = employeeRepository.findByPhoneContaining(employeePhone).get();
           return new ResponseEntity<>(employee, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /*
     * SEARCH ALL EMPLOYEES FROM DATABASE
     * URL : http://127.0.0.1:8080/employees/search/{keyword}
     */
    @GetMapping(path = "/employees/search/{keyword}")
    public ResponseEntity<?> searchEmployeeByNameAndEmailAndPhone(@PathVariable("keyword") String keyword){
       try {
           List<Employee> employees = employeeRepository.findByKeyword(keyword);
           if (employees.isEmpty())
               return new ResponseEntity<>(new ResponseMessage("Sorry, there are no such employees!"), HttpStatus.BAD_GATEWAY);
           return new ResponseEntity<>(employees, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /*
     * DELETE A EMPLOYEE BY ID FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/delete/{employeeId}
     */
    @DeleteMapping(path = "/employees/delete/{employeeId}")
    public  ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId){
       try {
           if (!employeeRepository.existsById(employeeId))
               return new ResponseEntity<>(new ResponseMessage("Employee does not exist : "+employeeId), HttpStatus.BAD_REQUEST);
           employeeRepository.deleteById(employeeId);
           return new ResponseEntity<>(new ResponseMessage("Employee has been deleted successfully : "+employeeId), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


    /*
     * DELETE ALL EMPLOYEE FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/delete/all
     */
    @DeleteMapping(path = "/employees/delete/all")
    public  ResponseEntity<?> deleteAllEmployees(){
       try {
           List<Employee> employees = employeeRepository.findAll();
           employeeRepository.deleteAll(employees);
           return new ResponseEntity<>(new ResponseMessage("Ops, you are deleted all employees!"), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /*
     * POST A NEW EMPLOYEE FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/new
     */
    @PostMapping(path = "/employees/new")
    public ResponseEntity<?> saveNewEmployee(@Valid @RequestBody EmployeeDto employeeDto){
       try {
           if (employeeRepository.existsByEmail(employeeDto.getEmail()))
               return new ResponseEntity<>(new ResponseMessage("Employee email already exist!"), HttpStatus.BAD_REQUEST);
           if (employeeRepository.existsByPhone(employeeDto.getPhone()))
               return new ResponseEntity<>(new ResponseMessage("Employee phone number already exist!"), HttpStatus.BAD_REQUEST);

           employeeDto.setEmployeeCode(UUID.randomUUID().toString());
           Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), employeeDto.getPhone(), employeeDto.getJobTitle(),
                   employeeDto.getEmployeeCode(), employeeDto.getDob(), employeeDto.getAddress(),
                   employeeDto.getGender(), employeeDto.getImageUrl() ,employeeDto.getActive());
           employeeRepository.save(employee);
           return new ResponseEntity<>(new ResponseMessage("Employee has been added successfully : "+employee.getName()), HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /*
     * UPDATE A EMPLOYEE BY ID FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/update/{employeeId}
     */
    @PutMapping(path = "/employees/update/{employeeId}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody EmployeeDto employeeDto){
       try {
           if (!employeeRepository.existsById(employeeId))
               return new ResponseEntity<>(new ResponseMessage("Employee does not exist : "+employeeId), HttpStatus.BAD_REQUEST);
           if (employeeRepository.existsByEmail(employeeDto.getEmail()) && employeeRepository.findByEmailContaining(employeeDto.getEmail()).get().getId() != employeeId)
               return new ResponseEntity<>(new ResponseMessage("Employee email already exist!"), HttpStatus.BAD_REQUEST);
           if (employeeRepository.existsByPhone(employeeDto.getPhone()) && employeeRepository.findByPhoneContaining(employeeDto.getPhone()).get().getId() != employeeId)
               return new ResponseEntity<>(new ResponseMessage("Employee phone number already exist!"), HttpStatus.BAD_REQUEST);

           Employee employee = employeeRepository.findById(employeeId).get();
           employee.setName(employeeDto.getName());
           employee.setEmail(employeeDto.getEmail());
           employee.setPhone(employeeDto.getPhone());
           employee.setJobTitle(employeeDto.getJobTitle());
           employee.setDob(employeeDto.getDob());
           employee.setAddress(employeeDto.getAddress());
           employee.setGender(employeeDto.getGender());
           employee.setImageUrl(employeeDto.getImageUrl());
           employee.setActive(employeeDto.getActive());
           employeeRepository.save(employee);
           return new ResponseEntity<>(new ResponseMessage("Employee has been updated successfully : "+employeeId), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("REQUEST ERROR :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
