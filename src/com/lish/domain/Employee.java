package com.lish.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by InnoXYZ on 14-5-28.
 */
public class Employee {


	@Setter @Getter
	private Integer id;
	@Setter @Getter
	private String name;
	@Setter @Getter
	private String email;
	@Setter @Getter
	private Date hiredate;

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Date getHiredate() {
//		return hiredate;
//	}
//
//	public void setHiredate(Date hiredate) {
//		this.hiredate = hiredate;
//	}

	public Employee(){

	}

	public Employee(String name, String email, Date hiredate) {
		this.name = name;
		this.email = email;
		this.hiredate = hiredate;
	}
}
