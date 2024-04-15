package dao.Impl;

import java.util.ArrayList;
import java.util.List;

import dao.StudentDao;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class StudentImpl implements StudentDao {
	private static String persistenceUnit = "Exer4Student mssql";
	private EntityManager em;
	
	public StudentImpl() {
		em = Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
	}

	@Override
	public List<Student> findByEnrollmentInYear(int year) {
		List<Student> list = new ArrayList<Student>();
		Query query = em.createNamedQuery("Student.findByEnrollmentInYear", Student.class);
		query.setParameter("year", year);
		list = query.getResultList();
		return list;

	}

	@Override
	public List<Student> findByEnrollmentInCourse(String title) {
		List<Student> list = new ArrayList<Student>();
		TypedQuery<Student> tq = em.createNamedQuery("Student.findByEnrollmentInCourse", Student.class);
		tq.setParameter("title", title);
		list = tq.getResultList();
		return list;
	}

	@Override
	public List<Student> findByEnrollmentInDepartment(String name) {
		List<Student> list = new ArrayList<Student>();
		TypedQuery<Student> tq = em.createNamedQuery("Student.findStudentsEnrolledInDepartment", Student.class);
		tq.setParameter("name", name);
		list = tq.getResultList();
		return list;
	}

	@Override
	public List<Student> findAll() {
		List<Student> list = new ArrayList<Student>();
		Query query = em.createNamedQuery("Student.findAll", Student.class);
		list = query.getResultList();
		return list;
	}

	@Override
	public boolean addStudent(Student student) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(student);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		EntityTransaction tx = em.getTransaction();
		if (student == null || student.getId() == 0) {
			return false;
		}
		Student existsStudent = em.find(Student.class, student.getId());
		if (existsStudent == null) {
			return false;
		}
		try {
			tx.begin();
			em.merge(student);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		EntityTransaction tx = em.getTransaction();
		Student student = em.find(Student.class, id);
		if (student == null) {
			return false;
		}
		try {
			tx.begin();
			em.remove(student);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Student findStudentById(int id) {
		Student student = em.find(Student.class, id);
		if (student == null) {
			return null;
		}
		return student;
	}

}
