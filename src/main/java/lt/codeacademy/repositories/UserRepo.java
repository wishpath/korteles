package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.User;

//TODO 11 - Create UserRepo interface
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	//TODO 12 - Add method findByUsername
	User findByUsername(String username);
}
