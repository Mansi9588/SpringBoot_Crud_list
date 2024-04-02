package com.employee.crudlist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.crudlist.customexception.BuisnessException;
import com.employee.crudlist.customexception.ControllerException;
import com.employee.crudlist.entity.Employee;
import com.employee.crudlist.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@ComponentScan(basePackages = {"com.employee.crudlist"})


public class EmployeeController {

@Autowired
	private EmployeeService employeeService;


@GetMapping
  public List<Employee> getAllEmployee(){
	return employeeService.getAllEmployees();
}

@GetMapping("/{id}")
  public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
	try {
		Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
		return employeeOptional.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
	}catch (BuisnessException e) {
		 ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
		 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch (Exception e) {
			 ControllerException ce = new ControllerException("611", "Something went wrong in Controller");
			 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		
}
	
}

@PostMapping
public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
	try {
          Employee createdEmployee = employeeService.createEmployee(employee);
          return new ResponseEntity<Employee>(createdEmployee, HttpStatus.OK);
	}catch (BuisnessException e) {
		 ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
		 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch (Exception e) {
			 ControllerException ce = new ControllerException("612", "Something went wrong in Controller");
			 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		
}
}

@PutMapping("/{id}")
public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee){
	try {
		Optional<Employee> updatedEmployeeOptional = employeeService.updateEmployee(id, updatedEmployee);
		return updatedEmployeeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	catch (BuisnessException e) {
		 ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
		 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch (Exception e) {
			 ControllerException ce = new ControllerException("612", "Something went wrong in Controller");
			 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
	try {
		 boolean deleted = employeeService.deleteEmployee(id);
		    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}catch (BuisnessException e) {
		 ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
		 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch (Exception e) {
			 ControllerException ce = new ControllerException("612", "Something went wrong in Controller");
			 return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
}

}
