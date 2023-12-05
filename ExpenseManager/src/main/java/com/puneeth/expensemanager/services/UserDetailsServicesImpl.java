package com.puneeth.expensemanager.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.puneeth.expensemanager.entities.User;
import com.puneeth.expensemanager.repos.UserRepo;

@Service
public class UserDetailsServicesImpl implements UserDetailsService{
    @Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(email);
		if(user==null)
			throw new UsernameNotFoundException("User name not found");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
}
}
