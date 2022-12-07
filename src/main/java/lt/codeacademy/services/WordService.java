package lt.codeacademy.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Word;
import lt.codeacademy.repositories.WordRepo;



@Service
public class WordService {
	
	@Autowired
	WordRepo rep;

	public void save(Word word){
		rep.save(word);
	}

	public Iterable<Word> findAll() {
		Iterable<Word> result = rep.findAll();
		if (result == null) return new ArrayList<Word>();
		return result;
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
	
	public void writeFromFileToDb() throws FileNotFoundException, IOException{
		
			System.out.println("failo skaitymas");
			File file = new File("1-4978.txt"); 								
			try (BufferedReader br = new BufferedReader(new FileReader(file))) { 		
				
				
				
				String wordx = null;
				String translation = null;
				Long frequencyNr = 0L;
				
				
				do {													
					frequencyNr += 1;			
					wordx = br.readLine();
					translation = br.readLine();
					rep.save(new Word(null, wordx, translation, frequencyNr));	
				} while(wordx!=null && translation!=null);
		}
	}
}
