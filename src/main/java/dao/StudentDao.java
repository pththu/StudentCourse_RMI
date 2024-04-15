package dao;

import java.util.List;

import entity.Student;

public interface StudentDao {
	public List<Student> findByEnrollmentInYear(int year);
	public List<Student> findByEnrollmentInCourse(String title);
	public List<Student> findByEnrollmentInDepartment(String name);
	public List<Student> findAll();
	public boolean addStudent(Student student);
	public boolean updateStudent(Student student);
	public boolean deleteStudent(int id);
	public Student findStudentById(int id);
	
}
