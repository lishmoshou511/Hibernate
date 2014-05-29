package com.lish.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lishuang on 2014/5/29.
 */
@Entity
public class Course {

	@Id
	@Column(name = "cid", nullable = false, insertable = true, updatable = true)
	@Getter @Setter private int cid;
	@Basic
	@Column(name = "cname", nullable = true, insertable = true, updatable = true, length = 255)
	@Getter @Setter private String cname;

	@Basic
	@Column(name = "ccredit", nullable = true, insertable = true, updatable = true)
	@Getter @Setter private Integer ccredit;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Course course = (Course) o;

		if (cid != course.cid) return false;
		if (ccredit != null ? !ccredit.equals(course.ccredit) : course.ccredit != null) return false;
		if (cname != null ? !cname.equals(course.cname) : course.cname != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = cid;
		result = 31 * result + (cname != null ? cname.hashCode() : 0);
		result = 31 * result + (ccredit != null ? ccredit.hashCode() : 0);
		return result;
	}
}
