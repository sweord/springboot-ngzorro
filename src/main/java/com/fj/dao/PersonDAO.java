package com.fj.dao;

import com.fj.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends CrudRepository<Person, Integer> {

	Person findByName(String name);
}
