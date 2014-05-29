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
public class Studcourse {
	@Id
	@Column(name = "stuCourseId", nullable = false, insertable = true, updatable = true)
	@Getter @Setter private int stuCourseId;

	@Basic
	@Column(name = "sid", nullable = true, insertable = true, updatable = true)
	@Getter @Setter private Integer sid;

	@Basic
	@Column(name = "cid", nullable = true, insertable = true, updatable = true)
	@Getter @Setter private Integer cid;

	@Basic
	@Column(name = "grade", nullable = true, insertable = true, updatable = true)
	@Getter @Setter private Integer grade;



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Studcourse that = (Studcourse) o;

		if (stuCourseId != that.stuCourseId) return false;
		if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
		if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
		if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = stuCourseId;
		result = 31 * result + (sid != null ? sid.hashCode() : 0);
		result = 31 * result + (cid != null ? cid.hashCode() : 0);
		result = 31 * result + (grade != null ? grade.hashCode() : 0);
		return result;
	}
}
