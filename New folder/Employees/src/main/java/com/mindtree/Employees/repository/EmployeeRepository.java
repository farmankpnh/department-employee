package com.mindtree.Employees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mindtree.Employees.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
     Employee findById(long id);
     
     @Query(value="Select * from employee WHERE dept_id=:dept_id ORDER BY name ACS", nativeQuery=true)
     List<Employee> getByDeptId(@Param("dept_id") long dept_id);
     
     @Query(value="Selcet * from employee WHERE dept_id=:dept_id ORDER BY age DESC", nativeQuery= true)
     List<Employee> getEmployeeByDepartmentWithDescAge(@Param("dept_id") long dept_id);
}
