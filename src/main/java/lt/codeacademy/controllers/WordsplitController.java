package lt.codeacademy.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	public String adddata(){
		wordsplitService.addData();
		return "redirect:/allwords";
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
		ArrayList<Wordsplit> selectedWordsplits = new ArrayList<Wordsplit>();	
		if (!wordService.findAll().iterator().hasNext()) {	// jei nerado žodžių		
			it = null;
		}			
		else {
			selectedWord = wordService.findById(selected_id).get(); //išspręsti null
			Iterable<Wordsplit> allWordsplits = wordsplitService.findAll();
			
			for (Wordsplit w: allWordsplits) {
				System.out.println("aš čia");
				if (w.getParent().equals(selectedWord)) // jei bent vienas split neturi tėvuko, lūžta programa
					selectedWordsplits.add(w);
					System.out.println(w);
			}	
		}
		model.addAttribute("words", it);
		model.addAttribute("selected", selectedWord );
		model.addAttribute("selectedmsg", "Selected word:");
		model.addAttribute("selectedws", selectedWordsplits);
		
		return "/wordsplit/list-wordsplit";
	}
	
	@GetMapping("/add/{id}")
	public String showNewWordForm(@PathVariable("id") long selected_id, Wordsplit wordsplit, Model model) { 
		Word word = wordService.findById(selected_id).get(); //išspręst, jei neranda id
		model.addAttribute("selected", word);
		return "/wordsplit/add-wordsplit";
		
	}
//	@GetMapping("/add")
//	public String showNewWordForm(Wordsplit wordsplit) {
////		Word word = wordService.findById(id).get(); //išspręst, jei neranda id
////		model.addAttribute("selected_word", word);
//		return "/wordsplit/add-wordsplit";
//		
//	}
	
	@PostMapping("/addw/{selected_id}")
	public String addWord(@PathVariable("selected_id") long selected_id, @Valid Wordsplit wordsplit, BindingResult result, Model model) {
		Word word = wordService.findById(selected_id).get(); //išspręst, jei neranda id
		if (result.hasErrors()) {
			return "/wordsplit/add-wordsplit";
		}
		wordsplit.setParent(word);
		wordsplitService.save(wordsplit);
		return "redirect:/wordsplit/filtered/" + selected_id;
	}
	
//	@PostMapping("/addw")
//	public String addWord(@Valid Wordsplit wordsplit, BindingResult result, Model model) {
////		Word word = wordService.findById(id).get(); //išspręst, jei neranda id
//		if (result.hasErrors()) {
//			return "/wordsplit/add-wordsplit";
//		}
////		wordsplit.setParent(word);
//		wordsplitService.save(wordsplit);
//		return "redirect:/wordsplit/list";
//	}
	
	
	
}
