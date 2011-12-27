package ch.bfh.cmf.services.security;

import java.util.Arrays;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ch.bfh.cmf.repositories.UserRepository;

@Service("userDetailsService")
public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	@Inject
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		if ("admin".equals(username))
			return new User(username, username, true, true, true, true,
					Arrays.asList(new GrantedAuthorityImpl("ROLE_ADMIN")));
		else if (userRepository.findByName(username) != null)
			return new User(username, username, true, true, true, true,
					Arrays.asList(new GrantedAuthorityImpl("ROLE_USER")));
		
		final String errorMsg = "Could not find user " + username + " in the database";
		logger.warn(errorMsg);
		throw new UsernameNotFoundException(errorMsg);
	}

}
