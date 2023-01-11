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

import lt.codeacademy.entities.Mema;
import lt.codeacademy.services.MemaService;
import lt.codeacademy.services.WordsplitService;

@Controller
@RequestMapping("/mema")
public class MemaController {
	@Autowired
	MemaService memaService;
	@Autowired
	WordsplitService wordsplitService;

	@GetMapping("/adddata")
	public String addDummyData(){
		memaService.addDummyData();
		return "redirect:/allmemas";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		Mema mema = memaService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Mama id:" + id));
	
		memaService.delete(mema);
		return 	"redirect:/mema/build";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Mema mema = memaService.findById(id) 
				.orElseThrow(() -> new IllegalArgumentException("Invalid Mema id:" + id));
		model.addAttribute("mema", mema);
		return "mema/update-mema";
	}
	
	@PostMapping("/update/{id}")
	public String updateWordsplit(@PathVariable("id") long id, @Valid Mema mema, BindingResult result, Model model) {
		if (result.hasErrors()) {
			mema.setId(id);				//??
			return "mema/update-mema";
		}
			Mema oldMema =  memaService.findById(id).get();
			oldMema.setNonLiteral(mema.getNonLiteral());
			memaService.save(oldMema);
		return 	"redirect:/mema/build";
	}
	



}
