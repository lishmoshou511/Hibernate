package com.lish.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by lishuang on 2014/5/30.
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1101127880092209479L;
	@Getter @Setter private Integer id;
	@Getter @Setter private String name;

	//通过一个集合来表示可以对应多个学生对象 one2many
	@Getter @Setter private Set<Person> personSet;
}

