package lt.codeacademy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.entities.Role;
import lt.codeacademy.entities.User;
import lt.codeacademy.services.UserService;
import lt.codeacademy.services.WordService;

import static lt.codeacademy.utils.Utils.*;
@Slf4j
@SpringBootApplication
public class App {
	


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		log.info("Started");
	
	}
	
	
	
	
	@Bean 
	CommandLineRunner run(UserService userService) {
		return args -> {
									
			if(!userService.getUsers().isEmpty()) return; //if data exists, quit 
			
			userService.save(new Role(null, USER)); // id, name
			userService.save(new Role(null, MANAGER));
			userService.save(new Role(null, ADMIN));
			userService.save(new Role(null, SUPER_ADMIN));
			
			userService.save(new User(null, "John Doe", "john", "1234", new ArrayList<>())); //id, name, username, password, Arraylist<Role> roles
			userService.save(new User(null, "Alice Wonder", "alice", "1234", new ArrayList<>()));
			userService.save(new User(null, "Bob Bingo", "bob", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("john", USER);
			userService.addRoleToUser("john", MANAGER);
			userService.addRoleToUser("alice", USER);
			userService.addRoleToUser("alice", MANAGER);
			userService.addRoleToUser("alice", ADMIN);
			userService.addRoleToUser("bob", USER);
			
			userService.updatePassword("bob", new BCryptPasswordEncoder().encode("1234") );
			userService.updatePassword("john", new BCryptPasswordEncoder().encode("1234") );
			userService.updatePassword("alice", new BCryptPasswordEncoder().encode("1234") );
		};
	}

}
