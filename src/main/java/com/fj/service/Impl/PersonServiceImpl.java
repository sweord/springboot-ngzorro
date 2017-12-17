package com.fj.service.Impl;

import com.fj.dao.PersonDAO;
import com.fj.entity.Person;
import com.fj.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	@Autowired
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public Person savePerson(String name) {
		Person person = new Person(name);
		return personDAO.save(person);
	}

	@Override
	public void removePerson(String name) {
		Person person = personDAO.findByName(name);
		personDAO.delete(person);
	}

	@Override
	public Person getPerson(String name) {
		return personDAO.findByName(name);
	}

	@Override
	public List<Person> listPersons() {
		return (List<Person>)personDAO.findAll();
	}
}
