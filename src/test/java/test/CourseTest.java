package test;

import java.util.List;

import dao.CourseDao;
import dao.Impl.CourseImpl;
import entity.Course;
import entity.OnlineCourse;
import entity.OnsiteCourse;

public class CourseTest {
	public static void main(String[] args) {
		CourseDao courseDao = new CourseImpl();

		// add course
//		Course course = new OnlineCourse("Java programming", 5, "www.java.com");
//		boolean result = courseDao.addCourse(course);
//		if (result) {
//			System.out.println("Course added successfully");
//		}

		// update course
//		Course course = courseDao.findCourseByTitle2("Java programming 3");
//		System.out.println(course);
//		course.setTitle("Java programming4");
//		boolean result = courseDao.updateCourse(course);
//		if (result) {
//			System.out.println("Course updated successfully");
//		}

		// delete course
//		boolean result = courseDao.deleteCourse(4064);
//		if (result) {
//			System.out.println("Course deleted successfully");
//		}
		// find all courses
//		List<Course> courses = courseDao.findAll();
//		for (Course course : courses) {
//			System.out.println(course);
//		}
		// find all onsite courses
//		List<OnsiteCourse> courses = courseDao.findAllOnsiteCourses();
//		for (OnsiteCourse course : courses) {
//			System.out.println(course);
//		}
//		// find online courses
//		List<Course> c = courseDao.findOnlineCourse();
//		for (Course course : c) {
//			System.out.println(course);
//		}
		// find courses with max credits
//		List<Course> c = courseDao.findCoursesWithMaxCredits();
//		for (Course course : c) {
//			System.out.println(course);
//		}
		// find courses by department name
		List<Course> c = courseDao.findCoursesByDepartmentName("Engineering");
		for (Course course : c) {
			System.out.println(course);
		}
	}
}
