package com.lish.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lishuang on 2014/5/30.
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 976397557169257916L;
	@Setter @Getter private Integer id;
	@Setter @Getter private String name;
	@Setter @Getter private Department dept;

	@Setter @Getter private IdCard idCard;

}
