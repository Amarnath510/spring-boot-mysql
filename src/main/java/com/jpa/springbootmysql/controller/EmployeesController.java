package com.jpa.springbootmysql.controller;

import com.jpa.springbootmysql.entity.Employee;
import com.jpa.springbootmysql.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class EmployeesController {

   @Autowired
   private EmployeeService employeeService;

   @GetMapping("/employees")
   public List<Employee> fetchEmployees() {
      return employeeService.getAllEmployees();
   }

   @GetMapping("/employees/{id}")
   public Employee fetchEmployee(@PathVariable Integer id) {
      return employeeService.getEmployeeById(id);
   }

   @GetMapping("/employees/name")
   public Employee fetchEmployeeByName(@RequestParam String name) {
      return employeeService.getEmployeeByName(name);
   }

   @PostMapping("/employees")
   public Employee saveEmployee(@RequestBody Employee  employee) {
      return employeeService.saveEmployee(employee);
   }

   @PostMapping("/employees/all")
   public List<Employee> saveEmployees(@RequestBody List<Employee> employees) {
      return employeeService.saveEmployees(employees);
   }

   @DeleteMapping("/employees/{id}")
   public String deleteEmployee(@PathVariable Integer id) {
      return employeeService.deleteById(id);
   }

   @PutMapping("/employees")
   public Employee updateEmployee(@RequestBody Employee employee) {
      return employeeService.updateEmployee(employee);
   }
}
