package lt.codeacademy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Word;
import lt.codeacademy.entities.Wordsplit;
import lt.codeacademy.repositories.WordRepo;
import lt.codeacademy.repositories.WordsplitRepo;


@Service
public class WordsplitService {
	
	@Autowired
	WordsplitRepo rep;
	@Autowired
	WordRepo wordrep;

	public void save(Wordsplit word){
		rep.save(word);
	}

	public Iterable<Wordsplit> findAll() {
		Iterable<Wordsplit> result = rep.findAll();
		if (result == null) return new ArrayList<Wordsplit>();
		return result;
	}

	public Optional<Wordsplit> findById(long id) {
		return rep.findById(id);
	}

	public void delete(Wordsplit word) {
		rep.delete(word);	
	}
	
	public void addData() {
//		Word word = new Word(null, "žodis", "vertimas", 1L, null);
//		wordrep.save(word);
		Wordsplit wordsplit = new Wordsplit(null, "žodužis", "vertimužis", wordrep.getById(1L));
//		List<Wordsplit> list = List.of(wordsplit);
//		word.setWordsplits(list);

		
		rep.save(wordsplit);

		
//		Word istrauktas = wordrep.getById(8L);		
//		Wordsplit dadetas = new Wordsplit(null, "2", "2", istrauktas);
//		rep.save(dadetas);
//		List<Wordsplit> atnaujintas = istrauktas.getWordsplits(); //tuscias sarasas
		
		
	}
}
