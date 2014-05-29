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
public class Student {
	@Id
	@Column(name = "sid", nullable = false, insertable = true, updatable = true)
	@Getter @Setter private int sid;

	@Basic
	@Column(name = "sname", nullable = true, insertable = true, updatable = true, length = 255)
	@Getter @Setter private String sname;


	@Basic
	@Column(name = "ssex", nullable = true, insertable = true, updatable = true, length = 2)
	@Getter @Setter private String ssex;

	@Basic
	@Column(name = "sdept", nullable = true, insertable = true, updatable = true, length = 10)
	@Getter @Setter private String sdept;


	@Basic
	@Column(name = "sage", nullable = true, insertable = true, updatable = true)
	@Getter @Setter private Integer sage;

	@Basic
	@Column(name = "saddress", nullable = true, insertable = true, updatable = true, length = 255)
	@Getter @Setter private String saddress;



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		if (sid != student.sid) return false;
		if (saddress != null ? !saddress.equals(student.saddress) : student.saddress != null) return false;
		if (sage != null ? !sage.equals(student.sage) : student.sage != null) return false;
		if (sdept != null ? !sdept.equals(student.sdept) : student.sdept != null) return false;
		if (sname != null ? !sname.equals(student.sname) : student.sname != null) return false;
		if (ssex != null ? !ssex.equals(student.ssex) : student.ssex != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = sid;
		result = 31 * result + (sname != null ? sname.hashCode() : 0);
		result = 31 * result + (ssex != null ? ssex.hashCode() : 0);
		result = 31 * result + (sdept != null ? sdept.hashCode() : 0);
		result = 31 * result + (sage != null ? sage.hashCode() : 0);
		result = 31 * result + (saddress != null ? saddress.hashCode() : 0);
		return result;
	}
}
