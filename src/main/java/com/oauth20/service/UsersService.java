package com.oauth20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauth20.UserHelper;
import com.oauth20.UsersPojo;
import com.oauth20.dao.UsersDBQuery;

@Service
public class UsersService implements UserDetailsService {

	@Autowired
	UsersDBQuery usersDBQuery;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UsersPojo usersPojo = null;
		try {
			usersPojo = usersDBQuery.getUserDetails(username);
			UserHelper userDetail = new UserHelper(usersPojo);
			return (UserDetails) userDetail;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

}
