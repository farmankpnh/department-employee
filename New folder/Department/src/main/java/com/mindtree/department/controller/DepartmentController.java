package com.mindtree.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.department.VO.RequestTemplate;
import com.mindtree.department.entity.Department;
import com.mindtree.department.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@PostMapping
	public Department add(@RequestBody Department dept)
	{
		return departmentService.add(dept);
	}
	
	@GetMapping
	public List<Department> list()
	{
		return departmentService.list();
	}
	@GetMapping("/{id}")
	public Department searcById(@PathVariable long id)
	{
		return departmentService.searchById(id);
	}
	
	@GetMapping("/all-department-employee")
	public List<RequestTemplate> listWithEmp()
	{
		return departmentService.listWithEmp();
	}
	
	@GetMapping("/department-with-employee/{dept_id}")
	public RequestTemplate specificDeptEmp(@PathVariable long dept_id)
	{
		return departmentService.specificDeptEmployee(dept_id);
	}

}
