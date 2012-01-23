package ch.bfh.cmf.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

@Named("securityPageBean")
@Scope("session")
public class SecurityPageBean {
	public String getUserName()
	{
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
