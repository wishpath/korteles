package lt.codeacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.entities.Word;
import lt.codeacademy.services.WordService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class WordRestController {
	@Autowired
	private WordService service;
	
	@GetMapping ("/words")
	public List<Word> getAllWords(){
		return service.findAllList();
	}
	
	@PostMapping("/words")
	public Word saveWordDetails(@RequestBody Word word) {
	return service.save(word);	
	}
	
	@PutMapping("/words")
	public Word updateWord(@RequestBody Word word) {
		return service.save(word);	
	}
	
	@GetMapping("/words/{id}")
	public Word getWordById(@PathVariable Long id) {
		return service.findById(id).get();
	}
	
	@DeleteMapping("/words/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
