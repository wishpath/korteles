package lt.codeacademy.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Word;
import lt.codeacademy.repositories.WordRepo;



@Service
public class WordService {
	
	@Autowired
	WordRepo rep;

	public Word save(Word word){
		return rep.save(word);
	}

	public List<Word> findAll() {
		List<Word> result = rep.findAll();
		if (result == null) return new ArrayList<Word>();
		return result;
	}
	
	public List<Word> findByText(String text) {
		List<Word> result = rep.findByText(text);
		if (result == null) return new ArrayList<Word>();
		return result;
	}
	
	public List<Word> findAllList() {
		return rep.findAll();
	}

	public Optional<Word> findById(long id) {
		return rep.findById(id);
	}

	public void delete(Word word) {
		rep.delete(word);	
	}
	
	public String getFirstLetter(Word word) {
		return String.valueOf(word.getWordx().charAt(0)).toLowerCase();
	}
	
	public void writeFromFileToDb(int howManyWords) throws FileNotFoundException, IOException{
		
			System.out.println("failo skaitymas");
			File file = new File("1-4978.txt"); 								
			try (BufferedReader br = new BufferedReader(new FileReader(file))) { 		
			
				String wordx = null;
				String translation = null;
				Long frequencyNr = 0L;
				int counter = 0;
						
				do {													
					frequencyNr += 1;			
					wordx = br.readLine();
					translation = br.readLine();
					rep.save(new Word(null, wordx, translation, frequencyNr, null));	//paskutinis null pridetas veliau
					counter++;
					System.out.println("reading: " + counter);
				} while(counter <= howManyWords && counter <= 4977); // veikia, bet jei yra laiko padaryt normaliai
//			} while(wordx!=null && translation!=null); //originali eilute, kazkas negerai, ko gero turi būt ||
				System.out.println("WordService: writeFromFileToDb(): loop baigėsi, skaityti baigė"); // nespausdina, ko gero negerai su sąlyga
		}
	}
	
	public Word getWordByWordx(String tryWordx){
		Iterable<Word> all = rep.findAll();
		for (Word w: all) {
			if (w.getWordx().equals(tryWordx)) {
				System.out.println("WordService: getWordByWordx: rado! ieškojo: " + tryWordx + "rado: " + w );
				return w;
			}
		}
		System.out.println("WordService: getWordByWordx: neberado!");
		return null;
	}

	public void deleteById(Long id) {
		rep.deleteById(id);	
		
	}

	public List<String> getAllFirstLetters() {
		List<String> all = rep.getAllFirstLetters();
		return all;
	}
}
