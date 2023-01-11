package lt.codeacademy.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.entities.Course;
import lt.codeacademy.repositories.CourseRepo;


@Service
public class CourseService {
	
	@Autowired
	MemaService memaService;
	@Autowired
	CourseRepo courseRep;



	public void saveCourse(Course course){
		courseRep.save(course);
	}

	public Iterable<Course> findAll() {
		Iterable<Course> result = courseRep.findAll();
		if (result == null) return new ArrayList<Course>();
		return result;
	}

	public Optional<Course> findById(long id) {
		return courseRep.findById(id);
	}

	public void delete(Course course) {
		courseRep.delete(course);	
	}

	
//	public void addDummyData() {
//		saveDummyCourse("Hi Friend", "hola", "amigo");
//		saveDummyCourse("To govern is to foresee. (It is better to prevent problems than to fix them. An ounce of prevention is worth a pound of cure.)",
//				"gobernar", "es", "prever");
//	}
//	
//	private void saveDummyCourse(String name, String ... wordForms) {
//		List<Mema> list = new ArrayList<>();
//		for (String wordForm : wordForms) {
//		list.add(memaService.getMemaByWordForm(wordForm));
//		}
//		Course course = new Course(null, name, list, null);  // gale null 20221219
//		courseRep.save(course);
//	}
//	
//	public void saveCourse(String name, Long ... memaIds) {
//		List<Mema> list = new ArrayList<>();
//		for (Long id : memaIds) {
//			list.add(memaService.getById(id));
//		}
//		Course course = new Course(null, name, list, null); // gale null 20221219
//		courseRep.save(course);
//	}
//	
//	public List<Mema> makeInsertableMemaList(String ... wordForms ){
//		List<Mema> list = new ArrayList<>();
//		for (String wordForm : wordForms) {
//		list.add(memaService.getMemaByWordForm(wordForm));
//		}
//		return list;
//	}

	public void save(@Valid Course course) {
		courseRep.save(course);
		
	}


	
}
