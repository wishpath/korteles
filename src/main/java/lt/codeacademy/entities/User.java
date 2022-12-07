package lt.codeacademy.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User { 
	

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	String username;
	String password;
	
	//Loads roles ASAP when User is loaded
	@ManyToMany(fetch = FetchType.EAGER) 
	Collection<Role> roles = new ArrayList<>(); 
}
