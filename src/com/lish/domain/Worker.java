package com.lish.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by InnoXYZ on 14-5-28.
 */

//按照规范，该Pojo需要序列化，目的是可以唯一的表示该对象。同时可以在网络和文件中传输。
@Entity
public class Worker implements Serializable{


	private static final long serialVersionUID = 6982729530314711191L;
	@Id
	@Column(nullable = false, insertable = true, updatable = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter @Getter private Integer id;

	@Basic
	@Column(nullable = true, insertable = true, updatable = true)
	@Setter @Getter private String name;

	@Basic
	@Column(nullable = true, insertable = true, updatable = true)
	@Setter @Getter private String email;

	@Basic
	@Column(nullable = true, insertable = true, updatable = true)
	@Setter @Getter private Date hiredate;

	public Worker(){

	}

	public Worker(String name, String email, Date hiredate) {
		this.name = name;
		this.email = email;
		this.hiredate = hiredate;
	}
}
