package dao.Impl;

import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import entity.Course;
import entity.OnlineCourse;
import entity.OnsiteCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CourseImpl implements CourseDao{
	private static String persistenceUnit = "Exer4Student mssql";
	private EntityManager em;
	
	
	public CourseImpl() {
		em = Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
	}

	@Override
	public boolean addCourse(Course course) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(course);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCourse(Course course) {
		EntityTransaction tx = em.getTransaction();
		if(course == null || course.getId() == 0) {
			return false;
		}
		Course exsitsCourse = em.find(Course.class, course.getId());
		if (exsitsCourse == null) {
			return false;
		}
		try {
			tx.begin();
			em.merge(course);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCourse(int id) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Course course = em.find(Course.class, id);
			em.remove(em.contains(course) ? course : em.merge(course));
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Course findCourseByID(int id) {
//		Object o =  em.find(Course.class, id);
//		if (o instanceof OnsiteCourse) {
//			return (OnsiteCourse) o;
//		}else {
//			return (OnlineCourse) o;	
//		}
		return em.find(Course.class, id);
	}

	@Override
	public Course findCourseByTitle2(String title) {
		String query = "SELECT c FROM Course c WHERE c.title = :title";
	 	TypedQuery<Course> tq = em.createQuery(query, Course.class);
	 	tq.setParameter("title", title);
		if (tq.getResultList().size() > 0) {
			return tq.getSingleResult();
		} else {
			return null;
		}
		
	}

	@Override
	public List<Course> findCourseByTitle(String title) {
		Query query = em.createNamedQuery("Course.findByTitle");
		query.setParameter("title", title);
//		List<Course> list = new ArrayList<Course>();
		if(query.getResultList().size() < 0) {
            return null;
		}
        return query.getResultList();
		
	}

	@Override
	public List<Course> findAll() {
		return em.createNamedQuery("Course.findAll", Course.class).getResultList();
	}

	@Override
	public List<OnsiteCourse> findAllOnsiteCourses() {
		return em.createNamedQuery("Course.findOnsiteCourses", OnsiteCourse.class).getResultList();
		
	}

	@Override
	public List<Course> findCoursesWithMaxCredits() {
		return em.createNamedQuery("Course.findCoursesWithMaxCredits", Course.class).getResultList();
	}

	@Override
	public List<Course> findOnlineCourse() {
		List<Course> list = new ArrayList<Course>();
		list = em.createNamedQuery("Course.findOnlineCourse", Course.class).getResultList();
		return list;
	}

	@Override
	public List<Course> findCoursesByDepartmentName(String name) {
		List<Course> list = em.createNamedQuery("Course.findCoursesByDepartmentName", Course.class).setParameter("name", name).getResultList();
		return list;
	}

}
