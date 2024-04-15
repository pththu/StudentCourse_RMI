package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


@Entity
@DiscriminatorValue("Student")
@NamedQueries({
	@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByEnrollmentInYear", query = "SELECT s FROM Student s WHERE year(s.enrollmentDate) = :year"),
    @NamedQuery(name = "Student.findStudentsEnrolledInCourse", 
    						query = "select s from Student s inner join s.studentGrades sg where sg.course.title like :title"),
    @NamedQuery(name = "Student.findStudentsEnrolledInDepartment",query = "select s from Student s inner join s.studentGrades sg where sg.course.department.name like :name")
})

public class Student extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "EnrollmentDate")
	private LocalDate enrollmentDate;
	@OneToMany(mappedBy = "student")
	private Set<StudentGrade> studentGrades;
	public Student() {
	}
	
	public Student(String firstName, String lastName, LocalDate enrollmentDate) {
		super(firstName, lastName);
		this.enrollmentDate = enrollmentDate;
	}

	/**
	 * @return the enrollmentDate
	 */
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	/**
	 * @param enrollmentDate the enrollmentDate to set
	 */
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return String.format("Student [enrollmentDate=%s, getId()=%s, getFirstName()=%s, getLastName()=%s]",
				enrollmentDate, getId(), getFirstName(), getLastName());
	}
}
