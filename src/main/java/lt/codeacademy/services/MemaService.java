package lt.codeacademy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Mema;
import lt.codeacademy.entities.Wordsplit;
import lt.codeacademy.repositories.MemaRepo;
import lt.codeacademy.repositories.WordsplitRepo;


@Service
public class MemaService {
	
	@Autowired
	WordsplitRepo wordsplitRep;
	@Autowired
	WordsplitService wordsplitService;
	@Autowired
	MemaRepo memaRep;



	public void saveMema(Mema mema){
		memaRep.save(mema);
	}

	public Iterable<Mema> findAll() {
		Iterable<Mema> result = memaRep.findAll();
		if (result == null) return new ArrayList<Mema>();
		return result;
	}

	public Optional<Mema> findById(long id) {
		return memaRep.findById(id);
	}

	public void delete(Mema mema) {
		memaRep.delete(mema);	
	}

	
	public void addDummyData() {
		saveDummyMema("Hi Friend", "hola", "amigo");
		saveDummyMema("To govern is to foresee. (It is better to prevent problems than to fix them. An ounce of prevention is worth a pound of cure.)",
				"gobernar", "es", "prever");
	}
	
	private void saveDummyMema(String nonLiteral, String ... wordForms) {
		List<Wordsplit> list = new ArrayList<>();
		for (String wordForm : wordForms) {
		list.add(wordsplitService.getWordsplitByWordForm(wordForm));
		}
		Mema mema = new Mema(null, nonLiteral, list, null);  // gale null 20221219
		memaRep.save(mema);
	}
	
	public void saveMema(String nonLiteral, Long ... wordsplitIds) {
		List<Wordsplit> list = new ArrayList<>();
		for (Long id : wordsplitIds) {
			list.add(wordsplitService.getById(id));
		}
		Mema mema = new Mema(null, nonLiteral, list, null); // gale null 20221219
		memaRep.save(mema);
	}
	
	public List<Wordsplit> makeInsertableWordsplitList(String ... wordForms ){
		List<Wordsplit> list = new ArrayList<>();
		for (String wordForm : wordForms) {
		list.add(wordsplitService.getWordsplitByWordForm(wordForm));
		}
		return list;
	}

	public void save(@Valid Mema mema) {
		memaRep.save(mema);
		
	}


	
}
