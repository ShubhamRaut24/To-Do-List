package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repo.TaskRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.model.Task;
import com.example.demo.model.User;


@RestController
@RequestMapping("user")
@CrossOrigin
public class Usercontroller {
	
	@Autowired
	UserRepo userrepo;
	@Autowired
	TaskRepo taskrepo;
	
	@RequestMapping("mydetails/mytokenstarts{token}")
	public User mydetails(HttpServletRequest request) 
	{
		User user=(User) request.getAttribute("user");
				return user;
	}
	
	@RequestMapping("deleteuser{userid}/mytokenstarts{token}")
	public String deleteuser(@PathVariable int userid,HttpServletRequest request) 
	{
		try {
		User user=(User) request.getAttribute("user");
		if(user.getId()==userid)
		{
			userrepo.delete(user);
			return "deleted sucessfully";
		}
		return "are nit user id tak";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Exception";
 		}
	}
	@RequestMapping("changestatus{taskid}/{status}/mytokenstarts{token}")
	public boolean changeStatus(@PathVariable int taskid,@PathVariable int status,HttpServletRequest request) 
	{
		try {
			User user=(User) request.getAttribute("user");
			List<Task> list=user.getTask();
			System.out.println(list);
			for (int i = 0; i < list.size(); i++) 
			{
				if(list.get(i).getId()==taskid)
				{
					list.get(i).setStatus(status);
					break;
					
				}
			}
			userrepo.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			 return false;
		}
	}
	@RequestMapping("editwork{taskid}/{work}/mytokenstarts{token}")
	public boolean editWork(@PathVariable int taskid, @PathVariable String work,HttpServletRequest request) 
	{
		try {
			User user=(User) request.getAttribute("user");
			List<com.example.demo.model.Task> list=user.getTask();
			for (int i = 0; i <list.size(); i++) 
			{
				if(list.get(i).getId()==taskid)
				{
					list.get(i).setWork(work);
					break;
				}
			}
			userrepo.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping("addtask/mytokenstarts{token}")
	public boolean addtask(@RequestBody Task task, HttpServletRequest request) 
	{
		try {
			User user= (User) request.getAttribute("user");
			user.getTask().add(task);
			userrepo.save(user);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping("deleteTask{taskid}/mytokenstarts{token}")
	public boolean deleteTask(@PathVariable int taskid,HttpServletRequest request) 
	{
		try {
			User user=(User) request.getAttribute("user");
			List<com.example.demo.model.Task> list=user.getTask();
			for (int i = 0; i <list.size(); i++) 
			{
				if(list.get(i).getId()==taskid)
				{
					list.remove(i);
					break;
				}
			}
			userrepo.save(user);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}