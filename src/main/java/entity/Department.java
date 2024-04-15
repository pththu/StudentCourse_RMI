package entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Department")
@NamedQueries({
	@NamedQuery(name = "Department.findAll", query = "select d from Department d"),
	@NamedQuery(name = "Department.countCoursesByDepartment", 
						query = "select d.id, count(c) as n from Department d join d.courses c group by d.id order by n"),
	@NamedQuery(name = "Department.countCoursesByDepartment2", 
						query = "select d, count(c) as n from Department d join d.courses c group by d.id order by n"),
	@NamedQuery(name = "Department.findDepartmentById", query = "select d from Department d where d.id = :id"),
	@NamedQuery(name = "Department.findDepartmentByName", query = "select d from Department d where d.name = :name"),
	@NamedQuery(name = "Department.findDepartmentWithMaxBudget", query = "select d from Department d where d.budget = (select max(d.budget) from Department d)")
})
public class Department {
	@Id
	@Column(name = "DepartmentID")
	private int id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Administrator")
	private int administator;
	@Column(name = "Budget")
	private double budget;
	@Column(name = "StartDate")
	private LocalDateTime startDate;
	@OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
	protected Set<Course> courses = new HashSet<Course>();

	public Department() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdministator() {
		return administator;
	}

	public void setAdministator(int administator) {
		this.administator = administator;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	public Department(int id, String name, int administator, double budget, LocalDateTime startDate) {
		super();
		this.id = id;
		this.name = name;
		this.administator = administator;
		this.budget = budget;
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", administator=" + administator + ", budget=" + budget
				+ ", startDate=" + startDate + "]";
	}

}
