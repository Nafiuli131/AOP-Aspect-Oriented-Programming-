package com.example.AOP.service;

import com.example.AOP.entity.Employee;
import com.example.AOP.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) throws Exception {
        if (employee.getName().length() < 2) {
            throw new Exception("Name length is too short");
        }
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employee){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee not found");
        }
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeByID(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
