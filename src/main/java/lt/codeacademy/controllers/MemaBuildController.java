package lt.codeacademy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.entities.Mema;
import lt.codeacademy.entities.Wordsplit;
import lt.codeacademy.services.MemaService;
import lt.codeacademy.services.WordsplitService;

@Controller
@RequestMapping("/mema")
public class MemaBuildController {
	@Autowired
	MemaService memaService;
	@Autowired
	WordsplitService wordsplitService;
	int size;
	
	@GetMapping("/build")
	public String showList(Model model) {
		Iterable<Mema> it = memaService.findAll();		
		if (!memaService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): it = null");
			it = null;
		}	
		Iterable<Wordsplit> wordsplits = wordsplitService.findAll();		
		if (!wordsplitService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): wordsplits = null");
			wordsplits = null;
		}

		model.addAttribute("memas", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected mema:");
		model.addAttribute("wordsplits", wordsplits);
		model.addAttribute("id1", null);
		model.addAttribute("id2", null);
		model.addAttribute("id3", null);
		return "/mema/build-mema";
	}
	
	@GetMapping("/build/{id1}")
	public String showList1 (@PathVariable("id1") Long id1, Model model) {
		Iterable<Mema> it = memaService.findAll();
		if (!memaService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): it = null");
			it = null;
		}	
		Iterable<Wordsplit> wordsplits = wordsplitService.findAll();
		
		for (Wordsplit x: wordsplits) if (size<1000) System.out.println("labas" + ++size + x.getWord() + x.getId());
		System.out.println("memabuildcontroller: showlist1: wordsplits.length: " + size );
		size = 0;
		Wordsplit wordsplit1 = wordsplitService.findById(id1).get();
//		if (!wordsplitService.findAll().iterator().hasNext()) {			
//			System.out.println("mema klasė: showList(): wordsplits = null");
//			wordsplits = null;
//		} else {
////			for ( Wordsplit ws: wordsplits ) {
////				if (ws.getId()==id1) wordsplit1test = ws;
////			}		
//			System.out.println("wordsplit1test: " + wordsplit1.getWord());
//		}	
		model.addAttribute("memas", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected mema:");
		model.addAttribute("wordsplits", wordsplits);
		model.addAttribute("id1", id1);
		model.addAttribute("wordsplit1", wordsplit1);
		model.addAttribute("id2", null);
		return "/mema/build-mema";
	}
	
	@GetMapping("/build/{id1}/{id2}")
	public String showList2 (@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
		Iterable<Mema> it = memaService.findAll();		
		if (!memaService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): it = null");
			it = null;
		}	
		Iterable<Wordsplit> wordsplits = wordsplitService.findAll();
		Wordsplit wordsplit1 = wordsplitService.findById(id1).get();
		Wordsplit wordsplit2 = wordsplitService.findById(id2).get();
//		if (!wordsplitService.findAll().iterator().hasNext()) {			
//			System.out.println("mema klasė: showList(): wordsplits = null");
//			wordsplits = null;
//		} else {
//			for ( Wordsplit ws: wordsplits ) {
//				if (ws.getId()==id1) wordsplit1 = ws;
//				if (ws.getId()==id2) wordsplit2 = ws;
//				
//			}
//		}	
		model.addAttribute("memas", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected mema:");
		model.addAttribute("wordsplits", wordsplits);
		model.addAttribute("id1", id1);
		model.addAttribute("id2", id2);
		model.addAttribute("wordsplit1", wordsplit1);
		model.addAttribute("wordsplit2", wordsplit2);
		return "/mema/build-mema";
	}
	
	@GetMapping("/build/{id1}/{id2}/{id3}")
	public String showList3 (@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3, Model model) {
		Iterable<Mema> it = memaService.findAll();		
		if (!memaService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): it = null");
			it = null;
		}	
		Iterable<Wordsplit> wordsplits = wordsplitService.findAll();
		Wordsplit wordsplit1 = wordsplitService.findById(id1).get();
		Wordsplit wordsplit2 = wordsplitService.findById(id2).get();
		Wordsplit wordsplit3 = wordsplitService.findById(id3).get();
//		if (!wordsplitService.findAll().iterator().hasNext()) {			
//			System.out.println("mema klasė: showList(): wordsplits = null");
//			wordsplits = null;
//		} else {
//			for ( Wordsplit ws: wordsplits ) {
//				if (ws.getId()==id1) wordsplit1 = ws;
//				if (ws.getId()==id2) wordsplit2 = ws;
//				if (ws.getId()==id3) wordsplit3 = ws;
//				
//			}
//		}	
		model.addAttribute("memas", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected mema:");
		model.addAttribute("wordsplits", wordsplits);
		model.addAttribute("id1", id1);
		model.addAttribute("id2", id2);
		model.addAttribute("id3", id3);
		model.addAttribute("wordsplit1", wordsplit1);
		model.addAttribute("wordsplit2", wordsplit2);
		model.addAttribute("wordsplit3", wordsplit3);
		return "/mema/build-mema";
	}
	
	@GetMapping("/build/{id1}/{id2}/{id3}/{id4}")
	public String showList4 (
			@PathVariable("id1") Long id1, 
			@PathVariable("id2") Long id2, 
			@PathVariable("id3") Long id3, 
			@PathVariable("id4") Long id4, 
			Model model) {
		Iterable<Mema> it = memaService.findAll();		
		if (!memaService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): it = null");
			it = null;
		}	
		Iterable<Wordsplit> wordsplits = wordsplitService.findAll();
		Wordsplit wordsplit1 = wordsplitService.findById(id1).get();
		Wordsplit wordsplit2 = wordsplitService.findById(id2).get();
		Wordsplit wordsplit3 = wordsplitService.findById(id3).get();
		Wordsplit wordsplit4 = wordsplitService.findById(id4).get();
//		if (!wordsplitService.findAll().iterator().hasNext()) {			
//			System.out.println("mema klasė: showList(): wordsplits = null");
//			wordsplits = null;
//		} else {
//			for ( Wordsplit ws: wordsplits ) {
//				if (ws.getId()==id1) wordsplit1 = ws;
//				if (ws.getId()==id2) wordsplit2 = ws;
//				if (ws.getId()==id3) wordsplit3 = ws;
//				if (ws.getId()==id4) wordsplit4 = ws;
//				
//			}
//		}	
		model.addAttribute("memas", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected mema:");
		model.addAttribute("wordsplits", wordsplits);
		model.addAttribute("id1", id1);
		model.addAttribute("id2", id2);
		model.addAttribute("id3", id3);
		model.addAttribute("id4", id4);
		model.addAttribute("wordsplit1", wordsplit1);
		model.addAttribute("wordsplit2", wordsplit2);
		model.addAttribute("wordsplit3", wordsplit3);
		model.addAttribute("wordsplit4", wordsplit4);
		return "/mema/build-mema";
	}
	
	@GetMapping("/build/{id1}/{id2}/{id3}/{id4}/{id5}")
	public String showList5 (
			@PathVariable("id1") Long id1, 
			@PathVariable("id2") Long id2, 
			@PathVariable("id3") Long id3, 
			@PathVariable("id4") Long id4, 
			@PathVariable("id5") Long id5, 
			Model model) {
		Iterable<Mema> it = memaService.findAll();		
		if (!memaService.findAll().iterator().hasNext()) {			
			System.out.println("mema klasė: showList(): it = null");
			it = null;
		}	
		Iterable<Wordsplit> wordsplits = wordsplitService.findAll();
		Wordsplit wordsplit1 = wordsplitService.findById(id1).get();
		Wordsplit wordsplit2 = wordsplitService.findById(id2).get();
		Wordsplit wordsplit3 = wordsplitService.findById(id3).get();
		Wordsplit wordsplit4 = wordsplitService.findById(id4).get();
		Wordsplit wordsplit5 = wordsplitService.findById(id5).get();
//		if (!wordsplitService.findAll().iterator().hasNext()) {			
//			System.out.println("mema klasė: showList(): wordsplits = null");
//			wordsplits = null;
//		} else {
//			for ( Wordsplit ws: wordsplits ) {
//				if (ws.getId()==id1) wordsplit1 = ws;
//				if (ws.getId()==id2) wordsplit2 = ws;
//				if (ws.getId()==id3) wordsplit3 = ws;
//				if (ws.getId()==id4) wordsplit4 = ws;
//				if (ws.getId()==id5) wordsplit5 = ws;
//				
//			}
//		}	
		model.addAttribute("memas", it);
		model.addAttribute("selected", null );
		model.addAttribute("selectedmsg", "Selected mema:");
		model.addAttribute("wordsplits", wordsplits);
		model.addAttribute("id1", id1);
		model.addAttribute("id2", id2);
		model.addAttribute("id3", id3);
		model.addAttribute("id4", id4);
		model.addAttribute("wordsplit1", wordsplit1);
		model.addAttribute("wordsplit2", wordsplit2);
		model.addAttribute("wordsplit3", wordsplit3);
		model.addAttribute("wordsplit4", wordsplit4);
		model.addAttribute("wordsplit5", wordsplit5);
		return "/mema/build-mema";
	}
	
	@GetMapping("/add/{id1}")
	public String saveMema1(@PathVariable("id1") Long id1, Model model) { 
		//Wordsplit wordsplit1 = wordsplitService.findById(id1).get(); //išspręst, jei neranda id
		memaService.saveMema(null, id1);
		return 	"redirect:/mema/build";	
	}
	@GetMapping("/add/{id1}/{id2}")
	public String saveMema2(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) { 
		//Wordsplit wordsplit1 = wordsplitService.findById(id1).get(); //išspręst, jei neranda id
		memaService.saveMema(null, id1, id2);
		return 	"redirect:/mema/build";	
	}
	@GetMapping("/add/{id1}/{id2}/{id3}")
	public String saveMema3(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3, Model model) { 
		//Wordsplit wordsplit1 = wordsplitService.findById(id1).get(); //išspręst, jei neranda id
		memaService.saveMema(null, id1, id2, id3);
		return 	"redirect:/mema/build";	
	}
	@GetMapping("/add/{id1}/{id2}/{id3}/{id4}")
	public String saveMema4(
			@PathVariable("id1") Long id1, 
			@PathVariable("id2") Long id2, 
			@PathVariable("id3") Long id3, 
			@PathVariable("id4") Long id4, 
			Model model) { 
		//Wordsplit wordsplit1 = wordsplitService.findById(id1).get(); //išspręst, jei neranda id
		memaService.saveMema(null, id1, id2, id3, id4);
		return 	"redirect:/mema/build";	
	}
	@GetMapping("/add/{id1}/{id2}/{id3}/{id4}/{id5}")
	public String saveMema5(
			@PathVariable("id1") Long id1, 
			@PathVariable("id2") Long id2, 
			@PathVariable("id3") Long id3, 
			@PathVariable("id4") Long id4, 
			@PathVariable("id5") Long id5, 
			Model model) { 
		//Wordsplit wordsplit1 = wordsplitService.findById(id1).get(); //išspręst, jei neranda id
		memaService.saveMema(null, id1, id2, id3, id4, id5);
		return 	"redirect:/mema/build";	
	}
}
