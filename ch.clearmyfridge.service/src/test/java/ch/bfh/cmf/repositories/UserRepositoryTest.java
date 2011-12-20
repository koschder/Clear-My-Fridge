package ch.bfh.cmf.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	private String name;

	@Test
	public void testSaveNewUser() {
		User john = johnDoe();
		userRepository.save(john);

		assertEquals(john, userRepository.findAll().iterator().next());
	}

	@Test
	public void testFindByName() {
		User john = johnDoe();
		userRepository.save(john);
		assertEquals(john, userRepository.findByName(name));
		assertNull(userRepository.findByName("otherName"));
	}

	private User johnDoe() {
		User user = new User();
		user.setEmail("john@doe.com");
		name = "John Doe";
		user.setName(name);
		return user;
	}

}
