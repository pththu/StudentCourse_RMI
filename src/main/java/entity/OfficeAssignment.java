package entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OfficeAssignment")
public class OfficeAssignment {
	
	@Column(name = "Location")
	private String location;
	@Column(name = "Timestamp",columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Timestamp timestamp;
	@Id
	@OneToOne
	@JoinColumn(name = "InstructorID")
	protected Instructor instructor;

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public OfficeAssignment() {
	}

	public OfficeAssignment(String location, Timestamp timestamp) {
		super();
		this.location = location;
		this.timestamp = timestamp;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "OfficeAssignment [location=" + location + ", timestamp=" + timestamp + "]";
	}

	
}
