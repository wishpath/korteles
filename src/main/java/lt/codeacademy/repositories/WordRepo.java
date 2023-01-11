package lt.codeacademy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Word;

@Repository
public interface WordRepo extends JpaRepository<Word,Long>{
	
	@Query ("SELECT w FROM Word w where w.wordx like (:text%)")
	List<Word> findByText(@Param("text") String text);

	@Query (value = "SELECT DISTINCT LEFT (w.wordx, 1) FROM Word w ORDER BY w.wordx", nativeQuery = true)
	List<String> getAllFirstLetters();
	
}
