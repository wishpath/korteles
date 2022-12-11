package lt.codeacademy.controllers;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.entities.Word;
import lt.codeacademy.services.WordService;



@Controller
@RequestMapping("/word")
public class WordsController {

	@Autowired
	WordService wordService;
	String currentCategory = "";

	@GetMapping	
	public String wordHome(Word word) {
		return "redirect:/word/list";
	}

	@GetMapping("/readfile") //reads words from text file
	public String readFile() throws FileNotFoundException, IOException{
		wordService.writeFromFileToDb();
		return "redirect:/word/list";
	}
	
	@GetMapping("/test")
	public String wordMainTest(Word word) {
		return "main";
	}
	
	@GetMapping("/add")
	public String showNewWordForm(Word Word) {
		return "/word/add-word";
	}
	
	@PostMapping("/addw")
	public String addWord(@Valid Word Word, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("add post error");
			return "/word/add-word";
		}
		wordService.save(Word);
		return "redirect:/word";
	}

	
	@GetMapping("/list")
	public String showWordList(Model model) {
		Iterable<Word> it = wordService.findAll();		
		if (!wordService.findAll().iterator().hasNext()) {			
			System.out.println("null");
			it = null;
		}			
		else {	
			HashSet<String> categories = new HashSet<String>();
			for (Word s: it) categories.add(wordService.getFirstLetter(s));
			model.addAttribute("categories", categories);
		}
		
		model.addAttribute("words", it);
		model.addAttribute("category", "All");  
		model.addAttribute("categorymsg", "Showing words, starting with letter:");
		currentCategory = "All"; // ar vis dar reik?
		return "/word/list-word";
	}

	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Word word = wordService.findById(id) // Word klasei tinka Optional<Word> :/
				.orElseThrow(() -> new IllegalArgumentException("Invalid Word Id:" + id));
		model.addAttribute("word", word);
		return "word/update-word";
	}

	@PostMapping("/update/{id}")
	public String updateWord(@PathVariable("id") long id, @Valid Word word, BindingResult result, Model model) {
		if (result.hasErrors()) {
			word.setId(id);
			return "word/update-word";
		}
		wordService.save(word);
		return "redirect:/word/list";
	}
	
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Word word = wordService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Word Id:" + id));
		System.out.println("radau " + word );
		model.addAttribute("word", word);
		return "word/show-word";
	}

	@GetMapping("/delete/{id}")
	public String deleteStraipnis(@PathVariable("id") long id, Model model) {
		Word word = wordService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		wordService.delete(word);
		return 	"redirect:/word/list";
	}
	
	@GetMapping("/filtered/{category}")
	public String showFilteredList(@PathVariable("category") String category, Model model) {
		Iterable<Word> it = wordService.findAll();		
		if (!wordService.findAll().iterator().hasNext()) {	// jei nerado žodžių		
			it = null;
		}			
		else {}
		ArrayList<Word> filtered = new ArrayList<Word>();
		HashSet<String> categories = new HashSet<String>();
		String matcher = "^" + category;
		
		for (Word s: it) {
			if (wordService.getFirstLetter(s).matches(matcher)) filtered.add(s);
			categories.add(wordService.getFirstLetter(s));
		}
		model.addAttribute("words", filtered);
		model.addAttribute("categorymsg", "Showing words, starting with letter: ");
		model.addAttribute("categories", categories);
		model.addAttribute("category", category);
		currentCategory = category; //ar vis dar reik?

		return "/word/list-word";
	}
	

	
	

}


