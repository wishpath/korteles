package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Wordsplit;

@Repository
public interface WordsplitRepo extends JpaRepository<Wordsplit,Long>{
	
	
}
