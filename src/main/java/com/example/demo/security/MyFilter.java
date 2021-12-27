package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Repo.UserRepo;
import com.example.demo.model.User;

@Component
@CrossOrigin
public class MyFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtil jwtutil;
	@Autowired
	UserRepo userrpo;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String path=request.getServletPath();
		if(path.startsWith("/login"))
		{
			filterChain.doFilter(request, response);
		}
		else
		{
			int index=path.indexOf("mytokenstarts")+13;
			String token=path.substring(index);
			String username=jwtutil.extractUsername(token);
			User user=userrpo.findByUsername(username);
			UserDetails userDetails=new UserDetailsSerice(user); 
			if(jwtutil.validateToken(token, userDetails))
			{
				request.setAttribute("user", user);
				filterChain.doFilter(request, response);
			}
			else
				throw new IOException();
		}
		
	}

}
