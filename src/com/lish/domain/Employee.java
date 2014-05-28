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

	public Employee(){

	}

	public Employee(String name, String email, Date hiredate) {
		this.name = name;
		this.email = email;
		this.hiredate = hiredate;
	}
}
