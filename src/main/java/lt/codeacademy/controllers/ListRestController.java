package lt.codeacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.entities.User;
import lt.codeacademy.entities.Word;
import lt.codeacademy.entities.Wordsplit;
import lt.codeacademy.services.UserServiceImpl;
import lt.codeacademy.services.WordService;
import lt.codeacademy.services.WordsplitService;

@RestController
public class ListRestController {
	@Autowired
	private UserServiceImpl service;
	@Autowired
	private WordService wordService;
	@Autowired
	private WordsplitService wordsplitService;
	
	
	@GetMapping("/allusers")
	public List<User> getallusersjason(){return  service.getUsers();}
	
	@GetMapping("/allwords")
	public ResponseEntity<Iterable<Word>> getWords(){
		return ResponseEntity.ok().body(wordService.findAll());
	}
	
	@GetMapping("/allwordsplits")
	public Iterable<Wordsplit> getWordsplits(){
		return wordsplitService.findAll();
	}
}
