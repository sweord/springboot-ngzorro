package com.fj.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private Date gmtCreate;
	private Date gmtModify;
	private String name;

	public Person() {
		name = "";
		gmtCreate = new Date();
		gmtModify = new Date();
	}

	public Person(String name) {
		this.name = name;
		gmtCreate = new Date();
		gmtModify = new Date();
	}
}
