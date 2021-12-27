package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repo.UserRepo;
import com.example.demo.model.LoginObject;
import com.example.demo.model.ReturnLoginObject;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.UserDetails;
import com.example.demo.security.UserDetailsSerice;

@RestController
@RequestMapping("login")
@CrossOrigin
public class loginController {
	@Autowired
	UserRepo userrepo;
	
	@Autowired
	JwtUtil jwtutil;
	
	@RequestMapping("login")
	public ReturnLoginObject login(@RequestBody LoginObject obj) 
	{
		if(obj!=null&&obj.getPassword()!=null&&obj.getUsername()!=null)
		{
			int cnt=userrepo.countByUsername(obj.getUsername());
			if(cnt==1)
			{
				User dbobj=userrepo.findByUsername(obj.getUsername());
				if(obj.getPassword().equals(dbobj.getPassword()))
				{
					UserDetails userDetails=new UserDetailsSerice(dbobj);
					String token=jwtutil.generateToken(userDetails);
					return new ReturnLoginObject(1, "login sucessful", token);
				}
				return new ReturnLoginObject(0, "wrong password", null);
			}
			else if(cnt==0)
				return new ReturnLoginObject(0, "username wrong", null);
			else
				return new ReturnLoginObject(0, "somthing wrong with system", null);
		}
		return new ReturnLoginObject(0, "incompleat info", null);
	}
	
	@RequestMapping("register")
	public String register(@RequestBody User user) 
	{
		if(user!=null&& user.getName()!=null&&user.getPassword()!=null&&user.getUsername()!=null)
		{
			if(userrepo.countByUsername(user.getUsername())==0)
			{
			  if(user.getPassword().length()>4)
			  {
				  userrepo.save(user);
				  return "successfull";
			  }
			  return "invalid password";
			}
			return "invalid username";
		}
		return "invalid data";
	}

}
