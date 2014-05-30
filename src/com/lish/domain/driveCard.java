package com.lish.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lishuang on 2014/5/30.
 */
public class DriveCard implements Serializable{
	private static final long serialVersionUID = 3620338034836676451L;
	@Setter @Getter private Integer id;
	@Setter @Getter private Date validateDate;
	@Setter @Getter private Person person;
}
