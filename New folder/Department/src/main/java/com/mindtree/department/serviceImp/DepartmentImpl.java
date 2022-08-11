package com.mindtree.department.serviceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mindtree.department.VO.Employee;
import com.mindtree.department.VO.RequestTemplate;
import com.mindtree.department.entity.Department;
import com.mindtree.department.repository.DepartmentRepository;
import com.mindtree.department.service.DepartmentService;

@Component
public class DepartmentImpl implements DepartmentService
{
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Department add(Department dept) {
		// TODO Auto-generated method stub
		return departmentRepository.save(dept);
	}

	@Override
	public List<Department> list() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Department update(long id, String name) {
		Department dept= departmentRepository.findById(id);
		dept.setName(name);
		return departmentRepository.save(dept);
	}

	@Override
	public Department searchById(long id) {
		
		if(departmentRepository.findById(id)==null)
		{
			return null;
			
		}
	 return departmentRepository.findById(id);	
	}

	@Override
	public List<RequestTemplate> listWithEmp() {
		List<RequestTemplate> fullList = new ArrayList<RequestTemplate>();
		List<Department> allDept= this.list();
		Iterator<Department> ir= allDept.iterator();
		while(ir.hasNext())
		{
			Department dpt= ir.next();
			ResponseEntity<Employee[]> response= restTemplate.getForEntity("http://EMPLOYEE-SERVICE/employee/employee-with-ascname/"+dpt.getId(), Employee[].class);

			Employee[] employees= response.getBody();
			List<Employee> emps = Arrays.asList(employees);
			RequestTemplate RTm = new RequestTemplate();
			RTm.setDepartment(dpt);
			RTm.setEmployeeList(emps);
			fullList.add(RTm);
		}
		return fullList;
		
	}

	@Override
	public RequestTemplate specificDeptEmployee(long dept_id) {
		Department department = this.searchById(dept_id);
		
		ResponseEntity<Employee[]> response= restTemplate.getForEntity("http://EMPLOYEE-SERVICE/employee/employee-with-desc-age/"+department.getId(),Employee[].class);
		
		Employee[] employee = response.getBody();
		List<Employee> employees= Arrays.asList(employee);
		RequestTemplate requestTemplate= new RequestTemplate();
		
		requestTemplate.setDepartment(department);
		requestTemplate.setEmployeeList(employees);
		
		return requestTemplate;
		
	}

}
