package com.mygg.sb.junseongTest;

import java.util.List;
import java.util.Random;

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
		private final JunUserRpeository2 userRepeository2;
		
		public List<JunTestUser> getAllUsers()
		{
			return userRepeository.findAll();
		}
		
	    public List<JunTestUser> getUserByName(String name) {
	        return userRepeository.findByName(name);
	    }
	    
	    public void createUser(String name, int age) {
	    	Random rand = new Random();
	    	userRepeository.save(new JunTestUser("" + rand.nextInt(age), name, "testEmail", age));
	    	userRepeository2.save(new JunTestUser2("" + rand.nextInt(age), name, "tttt", age));
	    }
	}
