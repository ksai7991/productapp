package com.krishna.first.productmanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userservice {
	@Autowired
	private UserRepository repo;
	

	
    public void saveuser(Users user) {
    	
    	Userservice us1=new Userservice();
    	
    	
    	repo.save(us1.constructuser(user));
    }
    
    public Users constructuser(Users user) {
    	BCryptPasswordEncoder passencoder= new BCryptPasswordEncoder();
    	Users user1=new Users();
    	user1.setEnabled(1);
    	user1.setRole("ROLE_USER");
    	user1.setUsername(user.getUsername());
    	user1.setPassword(passencoder.encode(user.getPassword()));
    	user1.setUser_id(user.getUser_id());
    	return user1;
    }
    
    
    public boolean checkuser(Users user) {
    	return repo.existsByUsername(user.getUsername());
    }

}
