package com.lish.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by lishuang on 2014/6/1.
 */
public class S {
	@Getter @Setter private Integer id;

	@Getter @Setter private String name;

	@Getter @Setter private Set<SC> scs;


}
