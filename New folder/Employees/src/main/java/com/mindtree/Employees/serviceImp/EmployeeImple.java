package com.mindtree.Employees.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mindtree.Employees.VO.Department;
import com.mindtree.Employees.VO.ResponseTemplate;
import com.mindtree.Employees.entity.Employee;
import com.mindtree.Employees.repository.EmployeeRepository;
import com.mindtree.Employees.service.EmployeeService;

@Component
public class EmployeeImple implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Employee add(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	@Override
	public List<Employee> list() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee searchById(long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id);
	}

	@Override
	public ResponseTemplate empWithDept(long empId) {
		// TODO Auto-generated method stub
		ResponseTemplate RT = new ResponseTemplate();
		Employee emp= employeeRepository.findById(empId);
		long dept_id = emp.getDept_id();
		Department dept= restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+dept_id, Department.class);
		RT.setDepartment(dept);
		RT.setEmployee(emp);
		return RT;
	}

	@Override
	public Employee assignDepartment(long empId, long deptId) {
		Employee emp= employeeRepository.findById(deptId);
		Department dept= restTemplate.getForObject("http://localhost:9191/department/"+deptId, Department.class);
		if(emp==null|| dept==null)
		{
		return null;
		}
		emp.setDept_id(deptId);
		employeeRepository.save(emp);
		return emp;
	}

	@Override
	public List<Employee> getByDeptId(long dept_id) {
		
		return employeeRepository.getByDeptId(dept_id);
	}

	@Override
	public List<Employee> getEmployeeBuDepsrtmentWithDescAge(long dept_id) {
		
		return employeeRepository.getEmployeeByDepartmentWithDescAge(dept_id);
	}
	

}
