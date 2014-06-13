package com.unicorn.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

//import com.unicorn.model.User;
import com.unicorn.repository.UserRepository;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    private UserRepository userRepo;
	  
    @SuppressWarnings("deprecation")
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException
    {
        System.out.println("Getting access details from employee dao !!" + username);
         
        com.unicorn.model.User user = userRepo.getUser(username);
        if (user== null)
	      throw new UsernameNotFoundException("User not found");
	    System.out.println("User found !!" + user.getName());
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    UserDetails authUser = (UserDetails) new User(username, user.getPassword(), true, true, true, true, authorities);
        return authUser;
    
    }	
}
