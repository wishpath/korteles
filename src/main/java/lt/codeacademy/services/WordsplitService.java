package lt.codeacademy.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Word;
import lt.codeacademy.entities.Wordsplit;
import lt.codeacademy.repositories.WordsplitRepo;


@Service
public class WordsplitService {
	
	@Autowired
	WordsplitRepo rep;
	@Autowired
	WordService wordService;

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
	
	public void addDummyData() {
		saveDummyWordsplit("el, la", "la", "the (female)");
		saveDummyWordsplit("el, la", "el", "the (male)");
		saveDummyWordsplit("el, la", "los", "the (males, plural)");
		saveDummyWordsplit("el, la", "las", "the (females, plural)");
		saveDummyWordsplit("hola", "hola", "hi");
		saveDummyWordsplit("amigo", "amigo", "friend");
		saveDummyWordsplit("gobernar", "gobernar", "to govern");
		saveDummyWordsplit("ser", "es", "is (now)");
		saveDummyWordsplit("prever", "prever", "to foresee");
	}
	
	public void splitAllWordsToWordsplits(){
		int counter = 0;
		Iterable<Word> words = wordService.findAll();
		for (Word w: words) {
			List<String> wsWords = Arrays.asList(w.getWordx().split("\\s*,\\s*"));
			for (String wsWord : wsWords) {
				List<String> wsTranslations = Arrays.asList(w.getTranslation().split("\\s*,\\s*"));
				for(String wsTranslation: wsTranslations) {
					Wordsplit saveableWordsplit = new Wordsplit(null, wsWord, wsTranslation, w, null);
					rep.save(saveableWordsplit);
					System.out.println(counter++ + w.getId()
							+ " " + w.getWordx() + " " + w.getTranslation() + ": "
							+ wsWord + " " + wsTranslation);
				}
			}

		}
		System.out.println("WordsplitService: splitAllWordsToWordsplits(): loop baigėsi, dalinti baigė");
	}
	
	//public void splitRegularVerbsToForms(){} // išasmenuoti ir iškaityti laikais taisyklingus veiksmažodžius
	
	public void saveDummyWordsplit(String tryParentWordx, String form, String translation) {
		Wordsplit saveableWordsplit = null;
		Word insertableParent = wordService.getWordByWordx(tryParentWordx);
		if(insertableParent != null) {
			saveableWordsplit = new Wordsplit(null, form, translation, insertableParent, null);
			rep.save(saveableWordsplit);
		} else {
			System.out.println("WordsplitService: insertableParent = null");		
		}
	}
	
	public Wordsplit getWordsplitByWordForm(String tryForm){
		Iterable<Wordsplit> all = rep.findAll();
		for (Wordsplit w: all) {
			if (w.getWord().equals(tryForm)) return w;
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public Wordsplit getById(Long id){
		return rep.getById(id);
	}

	public List<Wordsplit> getWordsplitsByWordId(Long selected_id) {
		return rep.getWordsplitsByWordId(selected_id);		 
	}
}
