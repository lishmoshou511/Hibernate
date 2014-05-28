package com.lish.domain;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by InnoXYZ on 14-5-28.
 */

//按照规范，该Pojo需要序列化，目的是可以唯一的表示该对象。同时可以在网络和文件中传输。
public class Employee implements Serializable{


	private static final long serialVersionUID = 6982729530314711191L;
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
