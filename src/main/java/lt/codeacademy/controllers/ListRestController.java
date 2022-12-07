package lt.codeacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.entities.User;
import lt.codeacademy.services.UserServiceImpl;

@RestController
public class ListRestController {
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping("/allusers")
	public List<User> getallusersjason(){return  service.getUsers();}
	
}
