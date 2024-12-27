package com.mygg.sb.junseongTest;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Getter
public class JunTestUserService
	{
		private final JunUserRpeository userRepeository;
		
		public List<JunTestUser> getAllUsers()
		{
			return userRepeository.findAll();
		}
		
	    public List<JunTestUser> getUserByName(String name) {
	        return userRepeository.findByName(name);
	    }
	    
	    public void createUser(String name, int age) {
	    	userRepeository.save(new JunTestUser("1", name, "testEmail", age));
	    }
	}
