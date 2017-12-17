package com.fj.service;

import com.fj.entity.Person;

import java.util.List;

public interface PersonService {

	/**
	 * save a new person
	 *
	 * @param name person's name
	 * @return new person
	 */
	Person savePerson(String name);


	/**
	 * remove a person
	 *
	 * @param name person's name
	 */
	void removePerson(String name);

	/**
	 * get a person
	 *
	 * @param name person's name
	 * @return person
	 */
	Person getPerson(String name);


	/**
	 * list all persons
	 *
	 * @return all persons
	 */
	List<Person> listPersons();
}
