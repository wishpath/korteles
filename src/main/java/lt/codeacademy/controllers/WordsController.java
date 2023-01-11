package lt.codeacademy.controllers;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
	String currentCategory = ""; // trint?

	@GetMapping	
	public String wordHome(Word word) {
		return "redirect:/word/list";
	}

	@GetMapping("/readfile") //reads words from text file
	public String writeFromFileToDb() throws FileNotFoundException, IOException{
		wordService.writeFromFileToDb(4978);
		return "redirect:/word/list";
	}
	
	@GetMapping("/test")
	public String wordMainTest(Word word) {
		return "main";
	}
	
	@GetMapping("/add")
	public String showNewForm(Word word) {
		return "/word/add-word";
	}
	
	@PostMapping("/addw")
	public String add(@Valid Word word, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("add post error");
			return "/word/add-word";
		}
		wordService.save(word);
		return "redirect:/word";
	}
	
	@GetMapping("/list")
	public String showList(Model model) {
		Iterable<Word> it = wordService.findAll();		
		if (!wordService.findAll().iterator().hasNext()) {			
			System.out.println("Words Controller: showList(): null (nerado words)");
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
	public String update(@PathVariable("id") long id, @Valid Word word, BindingResult result, Model model) {
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
		model.addAttribute("word", word);
		return "word/show-word";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		Word word = wordService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid word Id:" + id));
		wordService.delete(word);
		return 	"redirect:/word/list";
	}
	
	@GetMapping("/filtered/{category}")
	public String showFilteredList(@PathVariable("category") String category, Model model) {
		List<Word> words = wordService.findByText(category);		

		List<String> categories = wordService.getAllFirstLetters();
				
		model.addAttribute("words", words);
		model.addAttribute("categorymsg", "Showing words, starting with letter: ");
		model.addAttribute("categories", categories);
		model.addAttribute("category", category);
		currentCategory = category; //ar vis dar reik?
		return "/word/list-word";
	}
	

	
	

}


