package lt.codeacademy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.entities.Course;
import lt.codeacademy.entities.Mema;
import lt.codeacademy.services.CourseService;
import lt.codeacademy.services.MemaService;

@Controller
@RequestMapping("/stat")
public class StatController {
	
	@Autowired
	MemaService memaService;
	@Autowired
	CourseService courseService;

	@GetMapping("/stat")
	public String stats(Model model) {
		System.out.println("test1");
//		Iterable<Course> it = courseService.findAll();		
//		if (!courseService.findAll().iterator().hasNext()) {			
//			System.out.println("PracticeController: showLits: null (nerado courses)");
//			it = null;
//		}				
//		model.addAttribute("courses", it);
		System.out.println("test");
		return "/stat/stat";

	}
	
}
