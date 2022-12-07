package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Role;
import lt.codeacademy.entities.User;
//TODO 13 - Create RoleRepo interface
@Repository
public interface RoleRepo extends JpaRepository<Role,Long>{
	//TODO 14 - Add method findByName
	Role findByName(String name);
}
