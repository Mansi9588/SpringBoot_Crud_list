package com.employee.crudlist.entity;

public class Employee {
  
	private Long empId;
	private String empName;
	private String empPosition;
	
	
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	
	
	public Employee(Long empId, String empName, String empPosition) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPosition = empPosition;
	}	
	
}
