package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


@NamedQueries({ //JPQL
    @NamedQuery(name = "Course.findAll", query = "select c from Course c"),
    @NamedQuery(name = "Course.findByTitle", query = "select c from Course c where c.title like :title"),
    @NamedQuery(name = "Course.existsById", query = "select (count(c) > 0) from Course c where c.id = :id"),
    //SELECT * FROM Course WHERE [Credits] = (SELECT MAX([Credits]) FROM Course);
    @NamedQuery(name="Course.findCoursesWithMaxCredits", query = "select c from Course c where c.credits = (select max(credits) from Course)"),
    @NamedQuery(name = "Course.findOnsiteCourses", query = "select c from OnsiteCourse c"),
    @NamedQuery(name="Course.findOnlineCourse", query = "select c from OnlineCourse c" ),
    @NamedQuery(name = "Course.findCoursesByDepartmentName", query ="select c from Course c join c.department d where d.name = :name" )
})

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course {
	@Id
	@Column(name = "CourseID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Title")
	private String title;
	@Column(name = "Credits")
	private int credits;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DepartmentID")
	protected Department department;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CourseInstructor", 
        joinColumns = @JoinColumn(name = "CourseID"),
        inverseJoinColumns = @JoinColumn(name = "PersonID"))
	protected Set<Instructor> instructors = new HashSet<Instructor>();
	
	@OneToMany(mappedBy = "course")
	protected List<StudentGrade> studentGrades = new ArrayList<>();

	public Course() {
	}

	public Course(String title, int credits) {
		super();
		this.title = title;
		this.credits = credits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	public List<StudentGrade> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGrade> studentGrades) {
		this.studentGrades = studentGrades;
	}

	@Override
	public String toString() {
		return String.format("Course{id=%d, title='%s', credits=%d}", id, title, credits);
	}

}
