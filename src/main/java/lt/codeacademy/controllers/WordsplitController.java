package lt.codeacademy.controllers;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.entities.Word;
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
		if (!wordService.findAll().iterator().hasNext()) {	// jei nerado žodžių		
			it = null;
		}			
		else {
			selectedWord = wordService.findById(selected_id).get(); //išspręsti null
		}
	
		model.addAttribute("words", it);
		model.addAttribute("selected", selectedWord );
		model.addAttribute("selectedmsg", "Selected word:");
		
		return "/wordsplit/list-wordsplit";
	}
}
