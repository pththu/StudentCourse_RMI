package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "OnlineCourse")
public class OnlineCourse extends Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	
	
	public OnlineCourse() {
	}


	public OnlineCourse(String title, int credits, String url) {
		super(title, credits);
		this.url = url;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return String.format("OnlineCourse [url=%s, getId()=%s, getTitle()=%s, getCredits()=%s]", url, getId(),
				getTitle(), getCredits());
	}
}
