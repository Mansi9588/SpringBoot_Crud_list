package com.employee.crudlist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.crudlist.customexception.BuisnessException;
import com.employee.crudlist.entity.Employee;

@Service
public class EmployeeService {

	private List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    public List<Employee> getAllEmployees() {
    	try {
//    		List<Employee> empList = crudRepo.findAll();
    		if(employees.isEmpty())
    			throw new BuisnessException("604", "Hey List completely empty, we have nothing to return");
    		return employees;
    	}catch (Exception e) {
    		throw new BuisnessException("605", "Something went wrong in Service Layer while fetching all employees" + e.getMessage());
    	}
    }

    public Optional<Employee> getEmployeeById(Long id) {
    	try {
    		return employees.stream()
                    .filter(emp -> emp.getEmpId().equals(id))
                    .findFirst();
    	}catch(IllegalArgumentException e) {
    		throw new BuisnessException("606","Given Employee is null, please send some id to be searched" + e.getMessage());
       
    }catch( java.util.NoSuchElementException e) {
		throw new BuisnessException("607","Given Employee id does not exist in Database" + e.getMessage());
    }
    }
    	

    public Employee createEmployee(Employee employee) {
    	try {
    		if(employee.getEmpName().isEmpty()|| employee.getEmpName().length()== 0) {
    			throw new BuisnessException("601","Please send Proper Name, It's Blank");
    		}
    		employee.setEmpId(nextId++);
            employees.add(employee);
            return employee;
    	}catch(IllegalArgumentException e) {
    		throw new BuisnessException("602","Given Employee is null" + e.getMessage());
    	}catch(Exception e) {
    		throw new BuisnessException("603","Something went wrong in Service Layer" + e.getMessage());
    	}	
        
    }
    
    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
    	try {
    		 Optional<Employee> employeeOptional = getEmployeeById(id);
    	        if (employeeOptional.isPresent()) {
    	            Employee employee = employeeOptional.get();
    	            employee.setEmpName(updatedEmployee.getEmpName());
    	            employee.setEmpPosition(updatedEmployee.getEmpPosition());
    	            return Optional.of(employee);
    	        } else {
    	            return Optional.empty();
    	        }
    	}
    	catch(IllegalArgumentException e) {
    		throw new BuisnessException("612","Given Employee is null" + e.getMessage());
    	}catch(Exception e) {
    		throw new BuisnessException("613","Something went wrong in Service Layer" + e.getMessage());
        
        }
    }

    public boolean deleteEmployee(Long id) {
    	try {
    		Optional<Employee> employeeOptional = getEmployeeById(id);
            if (employeeOptional.isPresent()) {
                employees.remove(employeeOptional.get());
                return true;
            }
            return false;
    	}catch(IllegalArgumentException e) {
    		throw new BuisnessException("608","Given Employee is null, please send some id to be searched" + e.getMessage());
        
    }
    }
}
