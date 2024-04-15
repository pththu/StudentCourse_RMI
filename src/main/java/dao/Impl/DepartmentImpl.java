package dao.Impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.DepartmentDao;
import entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DepartmentImpl implements DepartmentDao {

	private static String persistenceUnit = "Exer4Student mssql";
	private EntityManager em;

	public DepartmentImpl() {
		em = Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
	}

	@Override
	public boolean addDepartment(Department department) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(department);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDepartment(Department department) {
		EntityTransaction tx = em.getTransaction();
		if (department == null || department.getId() == 0) {
			return false;
		}
		Department existsDepartment = em.find(Department.class, department.getId());
		if (existsDepartment == null) {
			return false;
		}
		try {
			tx.begin();
			em.merge(department);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Department findDepartmentById(int id) {
		Department department = em.find(Department.class, id);
		if (department == null) {
			return null;
		}
		return department;
	}

	@Override
	public Map<Department, Long> countCoursesByDepartment() {
//		List<Object[]> result = em
//				.createQuery("SELECT d, COUNT(c) FROM Department d JOIN d.courses c GROUP BY d", Object[].class)
//				.getResultList();
//		Map<Department, Long> map = new HashMap<>();
//		for (Object[] objects : result) {
//			map.put((Department) objects[0], (Long) objects[1]);
//		}
//		return map;
		Map<Department, Long> map = new LinkedHashMap<Department, Long>();

		List<?> results = em.createNamedQuery("Department.countCoursesByDepartment").getResultList();

		results.stream().map(o -> (Object[]) o).forEach(o -> {
			int departmentID = (int) o[0];
			Department department = em.find(Department.class, departmentID);
			long count = (long) o[1];
			map.put(department, count);
		});

		return map;
	}

	@Override
	public Map<Department, Long> countCoursesByDepartment2() {
		String query = "SELECT [DepartmentID] , COUNT(*) as cou FROM [dbo].[Course] GROUP BY [DepartmentID]";
		Map<Department, Long> map = new LinkedHashMap<Department, Long>();
		List<?> results = em.createNativeQuery(query).getResultList();
		results.stream().map(o -> (Object[]) o).forEach(o -> {
			int departmentID = (int) o[0];
			Department department = em.find(Department.class, departmentID);
			int count = (int) o[1];
			map.put(department, (long) count);
		});
		return map;
	}

	@Override
	public List<Department> findAll() {
//		List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
		List<Department> departments = em.createNamedQuery("Department.findAll").getResultList();
		return departments;
	}

	@Override
	public Department findDepartmentByName(String name) {
		Department department = (Department) em.createNamedQuery("Department.findDepartmentByName", Department.class)
				.setParameter("name", name).getSingleResult();
		return department;
	}

	@Override
	public List<Department> findDepartmentWithMaxBudget() {

		List<Department> departments = em.createNamedQuery("Department.findDepartmentWithMaxBudget", Department.class)
				.getResultList();
		return departments;
	}

}
