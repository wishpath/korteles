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

import lombok.RequiredArgsConstructor;
import lt.codeacademy.entities.Word;
import lt.codeacademy.services.UserService;
import lt.codeacademy.services.WordService;



@Controller
@RequestMapping("/word")
public class WordsController {

	@Autowired
	WordService service;
	String currentCategory = "";

	@GetMapping	
	public String wordHome(Word word) {
		return "redirect:/word/list";
	}

	@GetMapping("/readfile")
	public String readFile() throws FileNotFoundException, IOException{
		service.writeFromFileToDb();
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
		service.save(Word);
		return "redirect:/word";
	}

	
	@GetMapping("/list")
	public String showWordList(Model model) {
		Iterable<Word> it = service.findAll();		
		if (!service.findAll().iterator().hasNext()) {			
			System.out.println("null");
			it = null;
		}			
		else {	
			HashSet<String> categories = new HashSet<String>();
			for (Word s: it) categories.add(service.getFirstLetter(s));
			model.addAttribute("categories", categories);
		}
		
		model.addAttribute("words", it);
		model.addAttribute("category", "All");  // ar vis dar reik?
		model.addAttribute("categorymsg", "Showing words, starting with letter:");
		currentCategory = "All"; // ar vis dar reik?
		return "/word/list-word";
	}

	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Word word = service.findById(id) // Word klasei tinka Optional<Word> :/
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
		service.save(word);
		return "redirect:/word/list";
	}
	
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Word word = service.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Word Id:" + id));
		System.out.println("radau " + word );
		model.addAttribute("word", word);
		return "word/show-word";
	}

	@GetMapping("/delete/{id}")
	public String deleteStraipnis(@PathVariable("id") long id, Model model) {
		Word word = service.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		service.delete(word);
		return 	"redirect:/word/list";
	}
	
	@GetMapping("/filtered/{category}")
	public String showFilteredList(@PathVariable("category") String category, Model model) {
		Iterable<Word> it = service.findAll();		
		if (!service.findAll().iterator().hasNext()) {	// jei nerado straipsniu		
			it = null;
		}			
		else {}
		ArrayList<Word> filtered = new ArrayList<Word>();
		HashSet<String> categories = new HashSet<String>();
		String matcher = "^" + category;
		
		for (Word s: it) {
			if (service.getFirstLetter(s).matches(matcher)) filtered.add(s);
			categories.add(service.getFirstLetter(s));
		}
		model.addAttribute("words", filtered);
		model.addAttribute("categorymsg", "Showing words, starting with letter: ");
		model.addAttribute("categories", categories);
		model.addAttribute("category", category);
		currentCategory = category; //ar vis dar reik?

		
		return "/word/list-word";
	}
	

	
	

}


