package test;

import java.util.List;
import java.util.Map;

import dao.DepartmentDao;
import dao.Impl.DepartmentImpl;
import entity.Department;

public class DepartmentTest {
	public static void main(String[] args) {
		DepartmentDao departmentDao = new DepartmentImpl();

//		Map<Department,Long> map = departmentDao.countCoursesByDepartment();
//		for (Department department : map.keySet()) {
//			System.out.println(department.getName() + " has " + map.get(department) + " courses");
//		}
//		Map<Department,Long> map = departmentDao.countCoursesByDepartment2();
//		for (Department department : map.keySet()) {
//			System.out.println(department.getName() + " has " + map.get(department) + " courses");
//		}
//		List<Department> list = departmentDao.findAll();
//		for (Department department : list) {
//			System.out.println(department);
//		}
//		Department department = departmentDao.findDepartmentById(1);
//		System.out.println(department);
		List<Department> list = departmentDao.findDepartmentWithMaxBudget();
		for (Department department : list) {
			System.out.println(department);
		}
	}
}
