package lt.codeacademy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Word;
import lt.codeacademy.entities.Wordsplit;

@Repository
public interface WordsplitRepo extends JpaRepository<Wordsplit,Long>{

	
	
	@Query ("SELECT w FROM Wordsplit w where w.parent.id = (:selected_id)")
	List<Wordsplit> getWordsplitsByWordId(@Param("selected_id") Long id);
	
}
