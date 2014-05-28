package com.lish.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by InnoXYZ on 14-5-28.
 */
public class Employee {
	@Getter @Setter
	private int id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private Date hiredate;

}
