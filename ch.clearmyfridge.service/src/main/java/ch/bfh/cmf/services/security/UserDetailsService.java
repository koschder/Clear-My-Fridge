package ch.bfh.cmf.services.security;

import java.util.Arrays;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		if ("admin".equals(username))
			return new User(username, "", true, true, true, true, Arrays.asList(new GrantedAuthorityImpl("ROLE_ADMIN")));
		return new User(username, "", true, true, true, true, Arrays.asList(new GrantedAuthorityImpl("ROLE_USER")));
	}

}
