package lt.codeacademy.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.entities.Course;
import lt.codeacademy.services.CourseService;
import lt.codeacademy.services.MemaService;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	MemaService memaService;
	@Autowired
	CourseService courseService;

	@GetMapping("/list")
	public String showList(Model model) {
		Iterable<Course> it = courseService.findAll();		
		if (!courseService.findAll().iterator().hasNext()) {			
			System.out.println("CourseController: showLits: null (nerado courses)");
			it = null;
		}				
		model.addAttribute("courses", it);
		return "/course/list-course";
	}
	
	
	@GetMapping("/add")
	public String showNewForm(Course course, Model model) {
		model.addAttribute("memas", memaService.findAll());
		return "/course/add-course";
	}
	@PostMapping("/addw")
	public String add(@Valid Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("CourseController: add(): add post error");
			return "/course/add-course";
		}
		courseService.save(course);
		return "redirect:/course/list";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		Course course = courseService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
		courseService.delete(course);
		return 	"redirect:/course/list";
	}
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Course course = courseService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
		model.addAttribute("course", course);
		model.addAttribute("allmemas", memaService.findAll());
		model.addAttribute("checked", course.getMemas());
		return "course/update-course";
	}
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") long id, @Valid Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			course.setId(id);
			return "course/update-course";
		}
		courseService.save(course);
		return "redirect:/course/list";
	}
	
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Course course = courseService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid course id:" + id));
		model.addAttribute("course", course);
		model.addAttribute("counter", 0);
		return "course/show-course";
	}
	
}
