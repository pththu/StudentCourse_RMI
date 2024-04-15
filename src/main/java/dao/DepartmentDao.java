package dao;

import java.util.List;
import java.util.Map;

import entity.Department;

public interface DepartmentDao {
	// add department
	public boolean addDepartment(Department department);
	 // update department
	public boolean updateDepartment(Department department);
	// find department by id
	public Department findDepartmentById(int id);
	// count courses by department
	public Map<Department, Long> countCoursesByDepartment();
	
	public Map<Department, Long> countCoursesByDepartment2();
	// find all departments
	public List<Department> findAll();
	// find department by name
	public Department findDepartmentByName(String name);
	// find department with max budget
	public List<Department> findDepartmentWithMaxBudget();
}
