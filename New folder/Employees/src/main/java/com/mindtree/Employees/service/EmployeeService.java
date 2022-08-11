package com.mindtree.Employees.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.Employees.VO.ResponseTemplate;
import com.mindtree.Employees.entity.Employee;

@Service
public interface EmployeeService {

	Employee add(Employee emp);
	List<Employee> list();
	Employee searchById(long id);
	ResponseTemplate empWithDept(long empId);
	Employee assignDepartment(long empId, long deptId);
	List<Employee> getByDeptId(long dept_id);
	List<Employee> getEmployeeBuDepsrtmentWithDescAge(long dept_id);
	

}
