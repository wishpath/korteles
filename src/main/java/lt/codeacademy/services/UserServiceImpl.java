package lt.codeacademy.services;

import static lt.codeacademy.utils.Utils.USER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.entities.Role;
import lt.codeacademy.entities.User;
import lt.codeacademy.repositories.RoleRepo;
import lt.codeacademy.repositories.UserRepo;

//TODO 17 - Add implementation class for UserService
//TODO 18 - Add those annotations
@Service //used by Spring to use it as a bean 
@RequiredArgsConstructor //required for Lombok to create constructor
@Transactional //Used by JPA to make queries to DB transactional
@Slf4j //add logging capabilities to this class
//TODO 25 - Add implements UserDetailsService and implement needed method loadUserByUsername
public class UserServiceImpl implements UserService, UserDetailsService {
	
	//TODO 19 - Define needed repositories
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	//TODO 20 - Fill methods for UserServiceImpl
	@Override
	public User save(User user) {
		log.info("Saving user {} to the database",user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role save(Role role) {
		log.info("Saving role {} to the database",role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", username, roleName);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);	
		userRepo.save(user);
	}

	@Override
	public User getUser(String username) {		
		log.info("Fetching user {} from database",username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users from database");
		return userRepo.findAll();
	}
	
	@Override
	public void updatePassword(String username, String newPassword) {
		User user = getUser(username);
		user.setPassword(newPassword);
		userRepo.save(user);
	}

	//TODO 26 - Create method for UserDetailService interface
	//after this, we will be able to load our users from db for authentication
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//To get UserDetails we have to get a user from DB first
		User user = getUser(username);
		if(user == null) {
			log.error("User {} not found",username);
			throw new UsernameNotFoundException(username);
		}
		
		//We will return User from spring security package
		//so we need to get Authorities list from the user first
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		//getting roles from our user and adding them to authorities list
		user.getRoles().forEach( role -> {
			authorities.add(new SimpleGrantedAuthority( role.getName()));
		});
		
		//Next we have to create special User for Spring security
		var resultUser = new org.springframework.security.core.userdetails  //package name here
				.User(
					user.getName(),
					user.getPassword(),
					authorities						
				);
		//We have to return special type of User here
		return resultUser;
	}

	@Override
	public User registerNewUserAccount(User userDto) {
		Role role = roleRepo.findByName(USER);
		
		userDto.setRoles( List.of(role) );
		userDto.setPassword( new BCryptPasswordEncoder().encode( userDto.getPassword() )  );
		log.info("Saving new user " + userDto);
		return userRepo.save(userDto);
	}
	
	

}
