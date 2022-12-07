package lt.codeacademy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.entities.User;
import lt.codeacademy.services.UserService;
import lt.codeacademy.services.WordService;



@Controller
@RequiredArgsConstructor
public class MainController {
	
	@Autowired
	private final UserService userService;
	@Autowired
	WordService service;
	
	@GetMapping("/custom")
	public String home(WebRequest request, Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
		return "customLogin";
	}
	
	@GetMapping
	public String main() {
		return "main";
	}
	
	@GetMapping("/main")
	public String mainmain() {
		return "main";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "registration";
	}
	
	@PostMapping("/registration")
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user")  User userDto,  HttpServletRequest request,  Errors errors) {
	    
		User registered = userService.registerNewUserAccount(userDto);
	    return new ModelAndView("customLogin", "user", userDto);
	}
}

