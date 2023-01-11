package lt.codeacademy.controllers;

import java.util.ArrayList;
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
import lt.codeacademy.entities.Wordsplit;
import lt.codeacademy.services.WordService;
import lt.codeacademy.services.WordsplitService;

@Controller
@RequestMapping("/wordsplit")
public class WordsplitController {
	@Autowired
	WordService wordService;
	@Autowired
	WordsplitService wordsplitService;

	@GetMapping("/adddata")
	public String addDummyData(){
		wordsplitService.addDummyData();

		wordService.getWordByWordx("el, la");
		wordService.getWordByWordx("hola");
		wordService.getWordByWordx("amigo");
		wordService.getWordByWordx("gobernar");
		wordService.getWordByWordx("ser");
		wordService.getWordByWordx("prever");

		return "redirect:/";
	}
	
	@GetMapping("/splitwords")
	public String splitAllWordsToWordsplits(){
		wordsplitService.splitAllWordsToWordsplits();
		return "redirect:/";
	}
	
	
	
	@GetMapping("/list")
	public String showWordsplitOption(Model model) {
		Iterable<Word> it = wordService.findAll();		
		if (!wordService.findAll().iterator().hasNext()) {			
			System.out.println("wordsplit klasė: showWordsplitOption(): some null");
			it = null;
		}				
		model.addAttribute("words", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected word:");
		return "/wordsplit/list-wordsplit";
	}
	
	@GetMapping("/filtered/{selected_id}")
	public String showWordSplitSelected(@PathVariable("selected_id") Long selected_id, Model model) {
		Word selectedWord = null;
		Iterable<Word> it = wordService.findAll();		
		List<Wordsplit> selectedWordsplits = new ArrayList<Wordsplit>();	
		if (!wordService.findAll().iterator().hasNext()) {	// jei nerado žodžių		
			it = null;
		}			
		else {
			selectedWordsplits = wordsplitService.getWordsplitsByWordId(selected_id);
			selectedWord = wordService.findById(selected_id).get(); //išspręsti null
		}
		model.addAttribute("words", it);
		model.addAttribute("selected", selectedWord );
		model.addAttribute("selectedmsg", "Selected word:");
		model.addAttribute("selectedws", selectedWordsplits);
		
		return "/wordsplit/list-wordsplit";
	}
	
	@GetMapping("/add/{id}")
	public String showNewWordsplitForm(@PathVariable("id") long selected_id, Wordsplit wordsplit, Model model) { 
		Word word = wordService.findById(selected_id).get(); //išspręst, jei neranda id
		model.addAttribute("selected", word);
		return "/wordsplit/add-wordsplit";
		
	}
	
	@PostMapping("/addw/{selected_id}")
	public String addWordsplit(@PathVariable("selected_id") long selected_id, @Valid Wordsplit wordsplit, BindingResult result, Model model) {
		Word word = wordService.findById(selected_id).get(); //išspręst, jei neranda id
		if (result.hasErrors()) {
			return "/wordsplit/add-wordsplit";
		}
		wordsplit.setParent(word);
		wordsplitService.save(wordsplit);
		return "redirect:/wordsplit/filtered/" + selected_id;
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Wordsplit wordsplit = wordsplitService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Wordsplit Id:" + id));
		model.addAttribute("wordsplit", wordsplit);
		return "wordsplit/show-wordsplit";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteWordsplit(@PathVariable("id") long id, Model model) {
		Wordsplit wordsplit = wordsplitService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid wordsplit Id:" + id));
		Long parentId = wordsplit.getParent().getId();
		wordsplitService.delete(wordsplit);
		return 	"redirect:/wordsplit/filtered/" + parentId;
	}
	
	@GetMapping("/edit/{sid}/{id}")
	public String showUpdateForm(@PathVariable("id") long id, @PathVariable("sid") long sid, Model model) {
		Wordsplit wordsplit = wordsplitService.findById(id) 
				.orElseThrow(() -> new IllegalArgumentException("Invalid Word Id:" + id));
		model.addAttribute("wordsplit", wordsplit);
		return "wordsplit/update-wordsplit";
	}

	@PostMapping("/update/{sid}/{id}")
	public String updateWordsplit(@PathVariable("id") long id, @PathVariable("sid") long sid, @Valid Wordsplit wordsplit, BindingResult result, Model model) {
		if (result.hasErrors()) {
			wordsplit.setId(id);
			return "wordsplit/update-wordsplit";
		}
		Word parent = wordService.findById(sid).get();
		wordsplit.setParent(parent);
		wordsplitService.save(wordsplit);
		
		return "redirect:/wordsplit/filtered/" + parent.getId();
	}
	
	
	
}
