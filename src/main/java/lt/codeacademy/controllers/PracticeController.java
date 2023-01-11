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
@RequestMapping("/practice")
public class PracticeController {
	
	@Autowired
	MemaService memaService;
	@Autowired
	CourseService courseService;

	@GetMapping("/list")
	public String showList(Model model) {
		Iterable<Course> it = courseService.findAll();		
		if (!courseService.findAll().iterator().hasNext()) {			
			System.out.println("PracticeController: showLits: null (nerado courses)");
			it = null;
		}				
		model.addAttribute("courses", it);
		System.out.println("practice controller / list");
		return "/practice/list-practice";
	}
	
	@GetMapping("/practice/{id}/{mid}")
	public String practice(@PathVariable("id") long id, @PathVariable("mid") long mid, Model model) {
		Course course = courseService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid course id:" + id));
		Mema mema = memaService.findById(mid) 
				.orElseThrow(() -> new IllegalArgumentException("Invalid Mema id:" + mid));
		model.addAttribute("mema", mema);
		model.addAttribute("course", course);
		model.addAttribute("counter", 0);
		return "practice/show-practice";
	}
	
//	@GetMapping("/stats")
//	public String stats(Model model) {
//		Iterable<Course> it = courseService.findAll();		
//		if (!courseService.findAll().iterator().hasNext()) {			
//			System.out.println("PracticeController: showLits: null (nerado courses)");
//			it = null;
//		}				
//		model.addAttribute("courses", it);
//		System.out.println("test");
//		return "/practice/stat-practice";
//
//	}
	
}
