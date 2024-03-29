package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long>{
	
	Role findByName(String name);
}
