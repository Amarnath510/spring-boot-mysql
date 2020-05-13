package com.jpa.springbootmysql.service;

import static java.util.Optional.ofNullable;

import com.jpa.springbootmysql.entity.Employee;
import com.jpa.springbootmysql.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

   @Autowired
   private EmployeeRepository employeeRepository;

   public Employee saveEmployee(Employee employee)  {
      return employeeRepository.save(employee);
   }

   public List<Employee> saveEmployees(List<Employee> employees)  {
      return employeeRepository.saveAll(employees);
   }

   public Employee getEmployeeById(Integer id)  {
      return employeeRepository.findById(id).orElse(null);
   }

   public List<Employee> getAllEmployees()  {
      return employeeRepository.findAll();
   }

   public Employee getEmployeeByName(String name)  {
//      return employeeRepository.findByFirstNameOrLastName(name);
      return null;
   }

   public String deleteById(Integer id) {
      employeeRepository.deleteById(id);
      return "Deleted Employee " + id;
   }

   public Employee updateEmployee(Employee employee) {
      Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
      return ofNullable(existingEmployee)
            .map(employee1 -> employeeRepository.save(copyEmployee(employee, existingEmployee)))
            .orElse(null);
   }

   private Employee copyEmployee(Employee srcEmployee, Employee targetEmployee) {
      targetEmployee.setFirstName(srcEmployee.getFirstName());
      targetEmployee.setLastName(srcEmployee.getLastName());
      targetEmployee.setEmail(srcEmployee.getEmail());
      return targetEmployee;
   }
}
