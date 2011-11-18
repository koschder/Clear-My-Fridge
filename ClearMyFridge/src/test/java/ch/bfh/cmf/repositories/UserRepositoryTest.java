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
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void testSaveNewUser() {
		User johnDoe = johnDoe();
		userRepository.save(johnDoe);

		assertEquals(johnDoe, userRepository.findAll().iterator().next());
	}

	private User johnDoe() {
		User user = new User();
		user.setEmail("john@doe.com");
		user.setName("John Doe");
		return user;
	}

}
