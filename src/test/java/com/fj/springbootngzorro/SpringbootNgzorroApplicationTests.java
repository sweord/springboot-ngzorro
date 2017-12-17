package com.fj.springbootngzorro;

import com.fj.entity.Person;
import com.fj.service.Impl.PersonServiceImpl;
import com.fj.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootNgzorroApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testJpa() {
		PersonService service = context.getBean(PersonServiceImpl.class);
		service.savePerson("fj");
		service.savePerson("fyh");

		Person person = service.getPerson("fj");
		System.out.println(person.getId() + ":" + person.getName());

		person = service.getPerson("fyh");
		System.out.println(person.getId() + ":" + person.getName());

		List<Person> persons = service.listPersons();
		for (Person person1 : persons) {
			System.out.println(person1.getId() + ":" + person1.getName());
		}

		service.removePerson("fj");
		persons = service.listPersons();
		for (Person person1 : persons) {
			System.out.println(person1.getId() + ":" + person1.getName());
		}
	}
}
