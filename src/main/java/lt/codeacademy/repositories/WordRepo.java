package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Role;
import lt.codeacademy.entities.User;
import lt.codeacademy.entities.Word;

@Repository
public interface WordRepo extends JpaRepository<Word,Long>{
	
	
}
