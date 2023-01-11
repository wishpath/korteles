package lt.codeacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.entities.User;
import lt.codeacademy.entities.Word;
import lt.codeacademy.services.UserServiceImpl;
import lt.codeacademy.services.WordService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/list")
public class ListController {
	@Autowired
	private final UserServiceImpl userService;
	@Autowired
	private WordService wordService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@GetMapping("/words")
	public ResponseEntity<Iterable<Word>> getWords(){
		return ResponseEntity.ok().body(wordService.findAll());
	}

	
}


