package test;

import java.util.List;

import dao.StudentDao;
import dao.Impl.StudentImpl;
import entity.Student;

public class studentTest {
	public static void main(String[] args) {
		
		StudentDao studentDao = new StudentImpl();
		
		// get all students
//		List<Student> students = studentDao.findAll();
//		for (Student student : students) {
//			System.out.println(student);
//		}
		// get students by year
//		List<Student> students = studentDao.findByEnrollmentInYear(2005);
//		for (Student student : students) {
//			System.out.println(student);
//		}
		
		// get students by department name
		List<Student> students = studentDao.findByEnrollmentInDepartment("Engineering");
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
