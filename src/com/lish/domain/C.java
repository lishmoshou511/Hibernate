package com.lish.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by lishuang on 2014/6/1.
 */
public class C {
	@Getter @Setter Integer id;
	@Getter @Setter String name;
	@Getter @Setter Set<SC> scs;
}
